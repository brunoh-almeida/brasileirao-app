package br.com.brasileiraoapp.presentation.mapper

import br.com.brasileiraoapp.presentation.model.GameModel
import br.com.brasileiraoapp.result.GameResult
import br.com.brasileiraoapp.utils.Mapper

class GameResultToGameMapper(private val teamResultToTeamMapper: TeamResultToTeamMapper) :
    Mapper<GameResult, GameModel> {

    override fun mapFrom(from: GameResult) =
        GameModel(
            awayGoals = from.awayGoals.toString(),
            gameDate = from.gameDate,
            homeGoals = from.homeGoals.toString(),
            id = from.id,
            round = from.round,
            stadium = from.stadium,
            teamAway = teamResultToTeamMapper.mapFrom(from.teamAway),
            teamHome = teamResultToTeamMapper.mapFrom(from.teamHome)
        )
}