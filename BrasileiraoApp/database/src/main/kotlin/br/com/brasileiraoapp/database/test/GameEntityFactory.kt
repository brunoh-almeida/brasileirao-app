package br.com.brasileiraoapp.database.test

import br.com.brasileiraoapp.database.entity.GameEntity
import kotlin.random.Random

object GameEntityFactory {
    fun makeGameEntity(
        id: Long = Random.nextLong(),
        awayGoals: Int = Random.nextInt(),
        homeGoals: Int = Random.nextInt(),
        round: Int = Random.nextInt(),
        gameDate: String = "01/01/2020",
        stadium: String = "test-stadium",
        teamHomeName: String = "team-home-name",
        teamHomeShieldUrl: String = "any-home-url",
        teamAwayShieldUrl: String = "any-away-url",
        teamAwayName: String = "team-away-name"
    ) =
        GameEntity(
            id = id,
            awayGoals = awayGoals,
            homeGoals = homeGoals,
            round = round,
            gameDate = gameDate,
            stadium = stadium,
            teamHomeName = teamHomeName,
            teamHomeShieldUrl = teamHomeShieldUrl,
            teamAwayShieldUrl = teamAwayShieldUrl,
            teamAwayName = teamAwayName
        )
}