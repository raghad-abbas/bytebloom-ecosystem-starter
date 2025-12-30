package repositories
import datasource.CsvEcosystemDatasource
import domain.Team


class CsvTeamRepository() : TeamRepository {
    fun mappingTeamRawToTeam(dataTeams: CsvEcosystemDatasource): List<Team> {
        return dataTeams.getAllTeams().map { teamRaw ->
            Team(
                teamRaw.id, teamRaw.name, teamRaw.mentor, emptyList()
            )
        }
    }
    override fun getAllTeams(): List<Team> {
        val dataSource =CsvEcosystemDatasource()
        return mappingTeamRawToTeam(dataSource)
    }
}