package domain

import repositories.*
class EcosystemService(
    private val teamRepository: TeamRepository,
    private val menteeRepository: MenteeRepository,
    private val projectRepository: ProjectRepository,
    private val attendanceRepository: AttendanceRepository,
    private val performanceRepository: PerformanceSubmissionRepository
) {
    fun findLeadMentorForMentee(menteeId: String, allMentees: List<Mentee>, allTeams: List<Team>): String? {
        val mentee = allMentees.find { it.id == menteeId }
        val team = allTeams.find { it.id == mentee?.teamId }
        return team?.mentorLead
    }
}