package response

data class GameResponse (
    val awayGoals: Int,
    val gameDate: String,
    val homeGoals: Int,
    val id: Long,
    val round: Int,
    val stadium: String,
    val teamAway: TeamResponse,
    val teamHome: TeamResponse
)