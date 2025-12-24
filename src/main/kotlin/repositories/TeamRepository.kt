package repositories

import domain.Team

interface TeamRepository {
    fun getAllTeams(): List<Team>
}