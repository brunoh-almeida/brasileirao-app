package br.com.brasileiraoapp.presentation.model

import java.io.Serializable

data class GameModel(
    val awayGoals: String,
    val gameDate: String,
    val homeGoals: String,
    val id: Long,
    val round: Int,
    val stadium: String,
    val teamAway: TeamModel,
    val teamHome: TeamModel
) : Serializable