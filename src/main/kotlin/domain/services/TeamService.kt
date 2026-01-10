package domain.services

import domain.model.Team
import domain.repository.contracts.PerformanceSubmissionRepository
import domain.repository.contracts.TeamRepository

class TeamService(
    private val teamRepository: TeamRepository,
    private val performanceRepository: PerformanceSubmissionRepository
) {

    fun findLeadMentorForMentee(menteeId: String): String? {
        val allTeams = teamRepository.getAllTeams()
        val team = allTeams.find { team ->
            team.members.any { it.id == menteeId }
        }
        return team?.mentorLead
    }
    fun getOverallPerformanceAverageForTeam(teamId: String): Double {
        val team = teamRepository.getAllTeams()
            .find { it.id == teamId }
            ?: return 0.0
        val allScoring = team.members.flatMap { it.submissions }.map { it.score.toDouble() }
        return allScoring.average()

    }

}