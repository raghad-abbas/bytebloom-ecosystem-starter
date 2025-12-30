package datasource

import java.io.File
import models.*

val linesOfMentee = File("src/main/resources/mentees.csv").readLines().drop(n = 1)
val LinesOfTeams = File("src/main/resources/teams.csv").readLines().drop(n = 1)
val linesOfPerformance = File("src/main/resources/performance.csv").readLines().drop(n = 1)
val linesOfAttendance = File("src/main/resources/attendance.csv").readLines().drop(n = 1)
val linesOfProject = File("src/main/resources/projects.csv").readLines().drop(n = 1)

class CsvEcosystemDatasource : EcosystemDatasource {
    fun parseMenteeRaw(): List<MenteeRaw> {
        return linesOfMentee.map { line ->
            val columns = line.split(",")
            MenteeRaw(
                id = columns[0],
                name = columns[1],
                teamId = columns[2],
                emptyList(),
                emptyList()
            )
        }
    }

    fun parseTeamData(): List<TeamRaw> {
        val teamsList = mutableListOf<TeamRaw>()
        for (lineIndex in 0 until LinesOfTeams.size) {
            val columns = LinesOfTeams[lineIndex].split(",")
            val teamRecord = TeamRaw(
                columns[0],
                columns[1],
                columns[2],
                emptyList()
            )

            teamsList.add(teamRecord)
        }
        return teamsList
    }

    fun parsePerformanceSubmissionRaw(): List<PerformanceSubmissionRaw> {
        return linesOfPerformance.map { line ->
            val columns = line.split(",")
            PerformanceSubmissionRaw(
                menteeId = columns[0],
                id = columns[1],
                type = columns[2],
                score = columns[3]
            )
        }
    }

    fun parseAttendanceRaw(): List<AttendanceRaw> {
        return linesOfAttendance.map { line ->
            val columns = line.split(",")
            AttendanceRaw(
                menteeId = columns[0],
                week1 = columns[1],
                week2 = columns[2],
                week3 = columns[3]
            )
        }
    }

    fun parseProjectRaw(): List<ProjectRaw> {
        return linesOfProject.map { line ->
            val columns = line.split(",")
            ProjectRaw(
                id = columns[0],
                name = columns[1],
                teamId = columns[2]
            )
        }
    }

    private fun linkAttendanceWithMentee(attendance: List<AttendanceRaw>, mentees: List<MenteeRaw>): List<MenteeRaw> {
        return mentees.map { mentee ->
            val recordAttendance = attendance.filter { it.menteeId == mentee.id }
            mentee.copy(attendanceRecords = recordAttendance)
        }
    }

    private fun linkPerformanceSubmissionWithMentees(
        performanceSubmission: List<PerformanceSubmissionRaw>,
        mentees: List<MenteeRaw>
    ): List<MenteeRaw> {
        return mentees.map { mentee ->
            val performanceSubmission = performanceSubmission.filter { it.menteeId == mentee.id }
            mentee.copy(submissions = performanceSubmission)
        }
    }

    private fun aggregateMenteeData(): List<MenteeRaw> {
        val rawAttendanceList = parseAttendanceRaw()
        val rawPerformanceList = parsePerformanceSubmissionRaw()
        val rawMenteesList = parseMenteeRaw()
        val linkMenteesWithAttendance = linkAttendanceWithMentee(
            attendance = rawAttendanceList,
            mentees = rawMenteesList
        )
        val fullyLinkedMentees = linkPerformanceSubmissionWithMentees(
            performanceSubmission = rawPerformanceList,
            mentees = linkMenteesWithAttendance
        )

        return fullyLinkedMentees
    }

    private fun linkMenteesWithTeam(teams: List<TeamRaw>, mentees: List<MenteeRaw>): List<TeamRaw> {
        return teams.map { team ->
            val teamMentees = mentees.filter { it.teamId == team.id }
            team.copy(members = teamMentees)
        }
    }

    private fun aggregateTeamData(): List<TeamRaw> {
        val rawTeamList = parseTeamData()
        val menteeData = aggregateMenteeData()
        val linkTeamsWithMentees = linkMenteesWithTeam(rawTeamList, menteeData)
        return linkTeamsWithMentees
    }

    override fun getAllTeams(): List<TeamRaw> {
        return aggregateTeamData()
    }

    override fun getAllMenteeRaw(): List<MenteeRaw> {
        return aggregateMenteeData()
    }

    override fun getAllPerformanceSubmissionRaw(): List<PerformanceSubmissionRaw> {
        return parsePerformanceSubmissionRaw()
    }

    override fun getAllAttendanceRaw(): List<AttendanceRaw> {
        return parseAttendanceRaw()
    }

    override fun getAllProjectRaw(): List<ProjectRaw> {
        return parseProjectRaw()
    }
}