package br.com.brasileiraoapp.mapper

import response.GameResponse
import br.com.brasileiraoapp.result.GameResult
import br.com.brasileiraoapp.utils.Mapper

class GameResponseToGameResultMapper(private val teamResultMapper: TeamResponseToTeamResultMapper) : Mapper<GameResponse, GameResult> {
    override fun mapFrom(from: GameResponse): GameResult =
        GameResult(
            id = from.id,
            awayGoals = from.awayGoals,
            homeGoals = from.homeGoals,
            gameDate = from.gameDate,
            stadium = from.stadium,
            teamHome = teamResultMapper.mapFrom(from.teamHome),
            teamAway = teamResultMapper.mapFrom(from.teamAway),
            round = from.round
        )
}