package br.com.brasileiraoapp.mapper

import br.com.brasileiraoapp.result.GameResult
import br.com.brasileiraoapp.result.TeamResult
import br.com.brasileiraoapp.test.factory.GameResultFactory
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import org.junit.Test
import response.GameResponse
import response.TeamResponse
import kotlin.test.assertEquals

class GameResponseToGameResultMapperTest {

    private val teamResponseToTeamResultMapper: TeamResponseToTeamResultMapper = mockk()

    private val gameResponseToGameResultMapper =
        GameResponseToGameResultMapper(teamResponseToTeamResultMapper)

    @Test
    fun mapFrom_gameResponse_shouldReturnResult() {
        prepareScenario(
            teamResult1 = TeamResult("any1", "url1"),
            teamResult2 = TeamResult("any2", "url2")
        )

        val expected = GameResult(
            awayGoals = 2,
            gameDate = "2020202020",
            homeGoals = 3,
            teamHome = TeamResult("any1", "url1"),
            teamAway = TeamResult("any2", "url2"),
            id = 3,
            round = 1,
            stadium = "stadium"
        )

        val response = GameResponse(
            awayGoals = 2,
            gameDate = "2020202020",
            homeGoals = 3,
            teamHome = TeamResponse("any1", "url1"),
            teamAway = TeamResponse("any2", "url2"),
            id = 3,
            round = 1,
            stadium = "stadium"
        )

        val actual = gameResponseToGameResultMapper.mapFrom(response)

        assertEquals(expected, actual)
    }

    fun prepareScenario(
        teamResult1: TeamResult = GameResultFactory.makeTeamResult(),
        teamResult2: TeamResult = GameResultFactory.makeTeamResult()
    ) {
        val teamSlot = slot<TeamResponse>()
        every { teamResponseToTeamResultMapper.mapFrom(capture(teamSlot)) } answers {
            if (teamSlot.captured.name == "any1") teamResult1
            else teamResult2
        }

    }
}