import datasource.CsvEcosystemDatasource
import datasource.mapper.DataLinker
import datasource.parser.CsvParser
import domain.model.Mentee
import domain.model.PerformanceSubmission
import domain.model.Team
import domain.repository.internal.CsvMenteeRepository
import domain.repository.internal.CsvTeamRepository
import domain.repository.internal.CsvPerformanceSubmissionRepository
import domain.repository.mapping.DomainMapper
import domain.services.MenteeService
import domain.services.TeamService
import repositories.*

fun main() {
    fun main() {
        val parser = CsvParser()
        val linker = DataLinker()
        val datasource = CsvEcosystemDatasource(parser, linker)
        val mapper = DomainMapper()
        val teamRepo = CsvTeamRepository(datasource, mapper)
        val menteeRepo = CsvMenteeRepository(datasource, mapper)
        val performanceRepo = CsvPerformanceSubmissionRepository(datasource, mapper, menteeRepo)
        val teamService = TeamService(teamRepo, performanceRepo)
        val menteeService = MenteeService(menteeRepo, performanceRepo)
        println("--- TeamService Testing ---")
        val teamId = "alpha"
        val average = teamService.getOverallPerformanceAverageForTeam(teamId)
        println("Average performance for Team ($teamId): $average")


        val mentorLead = teamService.findLeadMentorForMentee("m001")
        println("Lead Mentor for mentee m001: $mentorLead")

        println("\n--- MenteeService Testing ---")

        val topMentee = menteeService.findTopScoringMenteeOverall()
        if (topMentee != null) {
            println("Top Scoring Mentee: ${topMentee.name} (ID: ${topMentee.id})")
        } else {
            println("No performance records found.")
        }
        val targetMenteeId = "m010"
        val breakdown = menteeService.getPerformanceBreakdownForMentee(targetMenteeId)
        println("Number of performance submissions for $targetMenteeId: ${breakdown.size}")
    }
}


//    val mentees = parseMenteeData()
//    val team = parseTeamData()
//    val performance = parsePerformanceData()
//
//    println("Mentee count = ${mentees.size}")
//    println("Team count = ${team.size}")
//    println("Performance rows = ${performance.size}")
//    println("____________________________________")
//    val builder = DomainBuilder()
//    val teams = builder.buildDomain(
//        performance.toMutableList(),
//        team.toMutableList(),
//        mentees.toMutableList()
//    )
//    val menteeList = builder.getMenteeList()
//    val firstTeam = teams.first()
//    println("First Team : ${firstTeam.name}")
//
//    val menteesInsideThisTeam = menteeList.filter { it.teamId == firstTeam.id }
//    println("Mentees names Within the team :")
//    menteesInsideThisTeam.forEach {
//        println(it.name)
//    }




