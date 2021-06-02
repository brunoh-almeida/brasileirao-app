package br.com.brasileiraoapp.mapper

import br.com.brasileiraoapp.database.entity.GameEntity
import br.com.brasileiraoapp.utils.Mapper
import response.GameResponse
import response.TeamResponse

class GameEntityToGameResponseMapper : Mapper<GameEntity, GameResponse> {
    override fun mapFrom(from: GameEntity) =
        GameResponse(
            id = from.id,
            round = from.round,
            awayGoals = from.awayGoals,
            homeGoals = from.homeGoals,
            gameDate = from.gameDate,
            stadium = from.stadium,
            teamAway = TeamResponse(from.teamAwayName, from.teamAwayShieldUrl),
            teamHome = TeamResponse(from.teamHomeName, from.teamHomeShieldUrl)
        )
}