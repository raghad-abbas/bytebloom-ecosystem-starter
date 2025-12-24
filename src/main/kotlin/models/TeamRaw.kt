package models

data class TeamRaw(
    val id :String,
    val name: String,
    val mentor: String,
    val members: List<MenteeRaw>
)