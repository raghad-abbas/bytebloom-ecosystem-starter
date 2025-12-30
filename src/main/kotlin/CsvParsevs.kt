import java.io.File
import models.PerformanceSubmissionRaw
import models.TeamRaw
import models.MenteeRaw


val performanceFileLines = File("src/main/resources/performance.csv").readLines().drop(1)
val teamFileLines = File("src/main/resources/teams.csv").readLines().drop(1)
val menteeFileLines = File("src/main/resources/mentees.csv").readLines().drop(1)

fun parsePerformanceData():List<PerformanceSubmissionRaw>{

    return performanceFileLines.map { line ->
        val columns = line.split(",")
        PerformanceSubmissionRaw(menteeId = columns[0],
            id= columns[1],
            type= columns[2],
            score= columns[3])
    }
}
fun parseTeamData(): List<TeamRaw> {
    val teamsList = mutableListOf<TeamRaw>()
    for (lineIndex in 0 until teamFileLines.size) {
        val columns = teamFileLines[lineIndex].split(",")
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
fun parseMenteeData(): List<MenteeRaw> {

    return menteeFileLines.map { line ->
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

