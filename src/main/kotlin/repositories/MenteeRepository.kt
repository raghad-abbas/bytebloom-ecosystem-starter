package repositories

import domain.Mentee

interface MenteeRepository {
    fun getAllMentees(): List<Mentee>
}