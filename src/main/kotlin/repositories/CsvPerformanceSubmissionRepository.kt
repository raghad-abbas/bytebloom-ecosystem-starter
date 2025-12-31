package repositories

import datasource.CsvEcosystemDatasource
import domain.PerformanceSubmission

class CsvPerformanceSubmissionRepository : PerformanceSubmissionRepository {
    fun mappingPerformanceSubmissionRawToPerformanceSubmission(datasource: CsvEcosystemDatasource):
            List<PerformanceSubmission> {
        return datasource.parsePerformanceSubmissionRaw().map { performanceSubmissionRaw ->
            PerformanceSubmission(
                performanceSubmissionRaw.id,
                performanceSubmissionRaw.type,
                performanceSubmissionRaw.score,
                performanceSubmissionRaw.menteeId
            )
        }
    }

    override fun getAllPerformanceSubmission(): List<PerformanceSubmission> {
        val dataSource = CsvEcosystemDatasource()
        return mappingPerformanceSubmissionRawToPerformanceSubmission(dataSource)
    }
}