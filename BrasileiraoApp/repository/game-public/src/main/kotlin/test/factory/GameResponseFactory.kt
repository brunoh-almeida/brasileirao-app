package test.factory

import io.github.serpro69.kfaker.Faker
import response.GameResponse
import response.TeamResponse
import java.util.*
import kotlin.random.Random

object GameResponseFactory {
    fun makeGameResponse(
        id: Long = Random.nextLong(),
        homeGoals: Int = Random.nextInt(),
        awayGoals: Int = Random.nextInt(),
        teamAway: TeamResponse = makeTeamResponse(),
        teamHome: TeamResponse = makeTeamResponse(),
        stadium: String = Faker().restaurant.name(),
        gameDate: String = Date().toString(),
        round: Int = Random.nextInt()
    ) =
        GameResponse(
            id = id,
            homeGoals = homeGoals,
            awayGoals = awayGoals,
            teamAway = teamAway,
            teamHome = teamHome,
            stadium = stadium,
            gameDate = gameDate,
            round = round
        )

    fun makeGameList(numberOfGames: Int): MutableList<GameResponse> {
        val gameList = mutableListOf<GameResponse>()
        for (x in 0 until numberOfGames) {
            gameList.add(makeGameResponse())
        }
        return gameList
    }

    fun makeTeamResponse(
        name: String = Faker().funnyName.name(),
        shieldUrl: String = Faker().internet.domain()
    ) =
        TeamResponse(name = name, shieldUrl = shieldUrl)
}