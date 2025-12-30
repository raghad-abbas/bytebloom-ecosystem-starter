package repositories
import datasource.CsvEcosystemDatasource
import domain.Mentee
class CsvMenteeRepository() : MenteeRepository {
    fun mappingMenteeRawToMentee(dataMentees: CsvEcosystemDatasource): List<Mentee> {
        return dataMentees.getAllMenteeRaw().map { menteeRaw ->
            Mentee(
                menteeRaw.id, menteeRaw.name, menteeRaw.teamId,emptyList(),emptyList()
            )
        }
    }
    override fun getAllMentees(): List<Mentee> {
        val dataSource =CsvEcosystemDatasource()
        return mappingMenteeRawToMentee(dataSource)
    }
}