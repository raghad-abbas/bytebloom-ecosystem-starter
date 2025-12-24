package repositories

import domain.Mentee
import models.MenteeRaw
import parseMenteeData
class CsvMenteeRepository() : MenteeRepository {
    fun mappingMenteeRawToMentee(dataMentees: List<MenteeRaw>): List<Mentee> {
        return dataMentees.map { menteeRaw ->
            Mentee(
                menteeRaw.id, menteeRaw.name, menteeRaw.teamId,emptyList(),emptyList()
            )
        }
    }
    override fun getAllMentees(): List<Mentee> {
        val parsedMentees = parseMenteeData()
        return mappingMenteeRawToMentee(parsedMentees)
    }
}