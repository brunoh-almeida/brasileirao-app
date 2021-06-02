package br.com.brasileiraoapp.test.factory

import br.com.brasileiraoapp.result.GameResult
import br.com.brasileiraoapp.result.TeamResult
import io.github.serpro69.kfaker.Faker
import java.util.*
import kotlin.random.Random

object GameResultFactory {
    fun makeGameResult(
        id: Long = Random.nextLong(),
        homeGoals: Int = Random.nextInt(),
        awayGoals: Int = Random.nextInt(),
        teamAway: TeamResult = makeTeamResult(),
        teamHome: TeamResult = makeTeamResult(),
        stadium: String = Faker().restaurant.name(),
        gameDate: String = Date().toString(),
        round: Int = Random.nextInt()
    ) =
        GameResult(
            id = id,
            homeGoals = homeGoals,
            awayGoals = awayGoals,
            teamAway = teamAway,
            teamHome = teamHome,
            stadium = stadium,
            gameDate = gameDate,
            round = round
        )

    fun makeGameList(numberOfGames: Int): MutableList<GameResult> {
        val gameList = mutableListOf<GameResult>()
        for (x in 0 until numberOfGames) {
            gameList.add(makeGameResult())
        }
        return gameList
    }

    fun makeTeamResult(
        name: String = Faker().funnyName.name(),
        shieldUrl: String = Faker().internet.domain()
    ) =
        TeamResult(name = name, shieldUrl = shieldUrl)
}