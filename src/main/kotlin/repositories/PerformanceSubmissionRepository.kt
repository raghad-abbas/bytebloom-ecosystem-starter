package repositories

import domain.PerformanceSubmission

interface PerformanceSubmissionRepository {
    fun getAllPerformanceSubmission(): List<PerformanceSubmission>
}