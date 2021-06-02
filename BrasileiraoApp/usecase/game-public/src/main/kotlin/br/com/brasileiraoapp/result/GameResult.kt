package br.com.brasileiraoapp.result

data class GameResult(
    val awayGoals: Int,
    val gameDate: String,
    val homeGoals: Int,
    val id: Long,
    val round: Int,
    val stadium: String,
    val teamAway: TeamResult,
    val teamHome: TeamResult
)