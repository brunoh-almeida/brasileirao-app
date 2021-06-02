package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.mapper.GameResponseToGameResultMapper
import br.com.brasileiraoapp.result.GameResult
import br.com.brasileiraoapp.result.TeamResult
import br.com.brasileiraoapp.test.factory.GameResultFactory
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import org.junit.Test
import repository.GameRepository
import response.GameResponse
import response.TeamResponse
import test.factory.GameResponseFactory
import kotlin.test.assertEquals

class GetGamesUseCaseTest {

    private val repository: GameRepository = mockk()
    private val gameResultMapper: GameResponseToGameResultMapper = mockk()

    private val getGamesUseCase: GetGamesUseCase = GetGamesUseCaseImpl(
        repository = repository,
        gameResultMapper = gameResultMapper
    )

    @Test
    fun invoke_shouldReturnSuccess() {
        runBlocking {

            val gamesResultExpected = listOf(
                GameResult(
                    awayGoals = 2,
                    gameDate = "2020202020",
                    homeGoals = 3,
                    teamHome = TeamResult("any1", "url1"),
                    teamAway = TeamResult("any2", "url2"),
                    id = 1,
                    round = 1,
                    stadium = "stadium"
                ),
                GameResult(
                    awayGoals = 2,
                    gameDate = "2020202020",
                    homeGoals = 3,
                    teamHome = TeamResult("any1", "url1"),
                    teamAway = TeamResult("any2", "url2"),
                    id = 1,
                    round = 1,
                    stadium = "stadium"
                )
            )

            val expected = Result.Success(gamesResultExpected)

            prepareScenario(
                getGamesResponse = NetworkResponse.Success(
                    listOf(
                        GameResponse(
                            awayGoals = 2,
                            gameDate = "2020202020",
                            homeGoals = 3,
                            teamHome = TeamResponse("any1", "url1"),
                            teamAway = TeamResponse("any2", "url2"),
                            id = 1,
                            round = 1,
                            stadium = "stadium"
                        ),
                        GameResponse(
                            awayGoals = 2,
                            gameDate = "2020202020",
                            homeGoals = 3,
                            teamHome = TeamResponse("any1", "url1"),
                            teamAway = TeamResponse("any2", "url2"),
                            id = 1,
                            round = 1,
                            stadium = "stadium"
                        )
                    )
                ),
                gameResultMapperResponse = GameResult(
                    awayGoals = 2,
                    gameDate = "2020202020",
                    homeGoals = 3,
                    teamHome = TeamResult("any1", "url1"),
                    teamAway = TeamResult("any2", "url2"),
                    id = 1,
                    round = 1,
                    stadium = "stadium"
                )
            )

            val actual = getGamesUseCase()

            assertEquals(expected, actual)
        }
    }

    @Test
    fun invoke_verifyGameMapperCall() {
        prepareScenario()
        runBlocking {
            getGamesUseCase()
            verify(exactly = 5) {
                gameResultMapper.mapFrom(any())
            }
        }
    }

    @Test
    fun invoke_shouldReturnError() {
        prepareScenario(
            getGamesResponse = NetworkResponse.Error(NetworkError("any", null))
        )

        val expected = Result.Error(ResultError.UnknownError())

        runBlocking {
            val actual = getGamesUseCase()
            assertEquals(expected,actual)
        }
    }

    private fun prepareScenario(
        getGamesResponse: NetworkResponse<List<GameResponse>, NetworkError> = NetworkResponse.Success(
            GameResponseFactory.makeGameList(5)
        ),
        gameResultMapperResponse: GameResult = GameResultFactory.makeGameResult()
    ) {
        coEvery {
            repository.getGames()
        } returns getGamesResponse

        every { gameResultMapper.mapFrom(any()) } returns gameResultMapperResponse
    }
}