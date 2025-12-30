import repositories.*
import domain.EcosystemService
fun main() {
    val mentees = parseMenteeData()
    val team = parseTeamData()
    val performance = parsePerformanceData()

    println("Mentee count = ${mentees.size}")
    println("Team count = ${team.size}")
    println("Performance rows = ${performance.size}")
    println("____________________________________")
//    val builder = DomainBuilder()
//    val teams = builder.buildDomain(
//            performance.toMutableList(),
//            team.toMutableList(),
//            mentees.toMutableList()
//        )
//    val menteeList = builder.getMenteeList()
//    val firstTeam = teams.first()
//    println("First Team : ${firstTeam.name}")
//
//    val menteesInsideThisTeam = menteeList.filter { it.teamId == firstTeam.id }
//    println("Mentees names Within the team :")
//    menteesInsideThisTeam.forEach {
//        println(it.name)
//          }

}






