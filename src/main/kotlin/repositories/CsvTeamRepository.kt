package repositories
import domain.Team
import models.TeamRaw
import parseTeamData

class CsvTeamRepository() : TeamRepository {
    fun mappingTeamRawToTeam(dataTeams: List<TeamRaw>): List<Team> {
        return dataTeams.map { teamRaw ->
            Team(
                teamRaw.id, teamRaw.name, teamRaw.mentor, emptyList()
            )
        }
    }
    override fun getAllTeams(): List<Team> {
        val parsedTeams = parseTeamData()
        return mappingTeamRawToTeam(parsedTeams)
    }
}