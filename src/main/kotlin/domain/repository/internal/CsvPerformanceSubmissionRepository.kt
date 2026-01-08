package domain.repository.internal

import domain.model.PerformanceSubmission
import domain.repository.contracts.PerformanceSubmissionRepository
import datasource.EcosystemDatasource
import domain.model.Mentee
import domain.repository.contracts.MenteeRepository
import domain.repository.mapping.DomainMapper


class CsvPerformanceSubmissionRepository(
    private val datasource: EcosystemDatasource,
    private val mapper: DomainMapper,
    private val menteeRepository: MenteeRepository
) : PerformanceSubmissionRepository {

    override fun getAllPerformanceSubmission(): List<PerformanceSubmission> {
        val rawData = datasource.getAllPerformanceSubmissions()
        return mapper.mapPerformanceSubmissionsRawToDomainList(rawData)
    }

    override fun getOverallPerformanceAverageForTeam(teamId: String): Double {
        return getAllPerformanceSubmission()
            .filter { it.id == teamId }
            .map { it.score.toDouble() }
            .average()
            .takeUnless { it.isNaN() } ?: 0.0
    }

    override fun findTopScoringMenteeOverall(): Mentee? {
        val topSubmission = getAllPerformanceSubmission()
            .maxByOrNull { it.score }
        return topSubmission?.let { submission ->
            menteeRepository.getMenteeById(submission.menteeId)
        }
    }


}
