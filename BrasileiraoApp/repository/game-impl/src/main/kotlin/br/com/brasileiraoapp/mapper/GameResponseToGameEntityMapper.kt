package br.com.brasileiraoapp.mapper

import br.com.brasileiraoapp.database.entity.GameEntity
import br.com.brasileiraoapp.utils.Mapper
import response.GameResponse
import response.TeamResponse

class GameResponseToGameEntityMapper : Mapper<GameResponse, GameEntity> {
    override fun mapFrom(from: GameResponse) =
        GameEntity(
            id = from.id,
            round = from.round,
            awayGoals = from.awayGoals,
            homeGoals = from.homeGoals,
            gameDate = from.gameDate,
            stadium = from.stadium,
            teamAwayName = from.teamAway.name,
            teamAwayShieldUrl = from.teamAway.shieldUrl,
            teamHomeName = from.teamHome.name,
            teamHomeShieldUrl = from.teamHome.shieldUrl
        )
}