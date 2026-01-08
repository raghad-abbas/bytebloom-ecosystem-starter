package domain.repository.contracts

import domain.model.Mentee
import domain.model.PerformanceSubmission

interface PerformanceSubmissionRepository {
    fun getAllPerformanceSubmission(): List<PerformanceSubmission>
    fun getOverallPerformanceAverageForTeam(teamId: String): Double
    fun findTopScoringMenteeOverall(): Mentee?
}