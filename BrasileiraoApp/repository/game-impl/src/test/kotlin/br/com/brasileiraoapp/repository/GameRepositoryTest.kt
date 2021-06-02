package br.com.brasileiraoapp.repository

import br.com.brasileiraoapp.api.GameApi
import br.com.brasileiraoapp.database.dao.GameDao
import br.com.brasileiraoapp.database.entity.GameEntity
import br.com.brasileiraoapp.database.test.GameEntityFactory
import br.com.brasileiraoapp.mapper.GameEntityToGameResponseMapper
import br.com.brasileiraoapp.mapper.GameResponseToGameEntityMapper
import br.com.brasileiraoapp.network.response.BaseResultResponse
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import response.GameResponse
import test.factory.GameResponseFactory

class GameRepositoryTest {

    private val gameApi: GameApi = mockk()

    private val gameResponseMapper: GameEntityToGameResponseMapper = mockk()
    private val gameEntityMapper: GameResponseToGameEntityMapper = mockk()
    private val gameDao: GameDao = mockk()
    private val gameRepository = GameRepositoryImpl(
        gameApi = gameApi,
        gameDao = gameDao,
        gameResponseMapper = gameResponseMapper,
        gameEntityMapper = gameEntityMapper
    )

    @Test
    fun getGames_ResultSuccess_EmptyList() {
        prepareScenario()
        val gamesExpected: NetworkResponse<List<GameResponse>, NetworkError> =
            NetworkResponse.Success(
                listOf()
            )

        var gamesResult: NetworkResponse<List<GameResponse>, NetworkError>
        runBlocking { gamesResult = gameRepository.getGames() }

        assert(gamesExpected is NetworkResponse.Success)
        assertEquals(gamesExpected, gamesResult)
    }

    @Test
    fun getGames_ResultSuccess_NotEmptyList() {
        val gameList = GameResponseFactory.makeGameList(4)
        prepareScenario(getGamesReturn = BaseResultResponse(gameList))

        val gamesExpected = NetworkResponse.Success(gameList)
        var gameResult: NetworkResponse<List<GameResponse>, NetworkError>
        runBlocking { gameResult = gameRepository.getGames() }

        assert(gameResult is NetworkResponse.Success)
        assertEquals(gameResult, gamesExpected)
    }

    @Test
    fun getGames_ResultError() {
        coEvery {
            gameApi.getGames()
        } throws Exception("msg")

        val expected = NetworkResponse.Error(NetworkError("msg", null))
        runBlocking {
            val result = gameRepository.getGames()
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameById_ResultSuccess_NotEmptyList() {
        val gamesExpecteds = GameResponseFactory.makeGameResponse()
        val expected = NetworkResponse.Success(gamesExpecteds)
        prepareScenario(gamebyIdResult = BaseResultResponse(gamesExpecteds))

        runBlocking {
            val result = gameRepository.getGamesById(12)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameById_ResultSuccess_VerifyRoomCall() {
        prepareScenario(gamebyIdResult = BaseResultResponse(null))

        runBlocking {
            gameRepository.getGamesById(12)
            coVerify(exactly = 1) {
                gameDao.getGame(12)
            }
        }
    }

    @Test
    fun getGameById_ResultSucess_EmptyList() {
        prepareScenario(gamebyIdResult = BaseResultResponse(null))
        val expected =
            NetworkResponse.Error(NetworkError("Não foram encontrado resultados", null))

        runBlocking {
            val call = gameRepository.getGamesById(12)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Error)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameById_ResultError() {
        prepareErrorScenario()

        val expected = NetworkResponse.Error(NetworkError("msg", null))

        runBlocking {
            val call = gameRepository.getGamesById(12)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Error)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByTeam_ResultSucess_NotEmptyList() {
        val gamesExpecteds = GameResponseFactory.makeGameList(6)
        val expected = NetworkResponse.Success(gamesExpecteds)
        prepareScenario(getGameByTeam = BaseResultResponse(gamesExpecteds))

        runBlocking {
            val result = gameRepository.getGamesByTeam(12)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByTeam_ResultSucess_EmptyList() {
        prepareScenario(getGameByTeam = BaseResultResponse(null))
        val expected = NetworkResponse.Success(listOf<GameResponse>())


        runBlocking {
            val call = gameRepository.getGamesByTeam(12)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Success)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByTeam_ResultError() {
        coEvery {
            gameApi.getGamesByTeam(any())
        } throws Exception("msg")

        val expected = NetworkResponse.Error(NetworkError("msg", null))

        runBlocking {
            val call = gameRepository.getGamesByTeam(12)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Error)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByDay_ResultSucess_NotEmptyList() {
        val gamesExpecteds = GameResponseFactory.makeGameList(6)
        val expected = NetworkResponse.Success(gamesExpecteds)
        prepareScenario(getGameByDate = BaseResultResponse(gamesExpecteds))

        runBlocking {
            val result = gameRepository.getGamesByDay("12/11/2020")
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByDay_ResultSucess_EmptyList() {
        prepareScenario(getGameByTeam = BaseResultResponse(null))
        val expected = NetworkResponse.Success(listOf<GameResponse>())


        runBlocking {
            val call = gameRepository.getGamesByDay("12/11/2020")
            val result = call.mapError { it }
            assert(result is NetworkResponse.Success)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByDate_ResultError() {
        coEvery {
            gameApi.getGamesByDay(any())
        } throws Exception("msg")

        val expected = NetworkResponse.Error(NetworkError("msg", null))

        runBlocking {
            val call = gameRepository.getGamesByDay("12/11/2020")
            val result = call.mapError { it }
            assert(result is NetworkResponse.Error)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByRound_ResultSucess_NotEmptyList() {
        val gamesExpecteds = GameResponseFactory.makeGameList(6)
        val expected = NetworkResponse.Success(gamesExpecteds)
        prepareScenario(getGamesByRoundReturn = BaseResultResponse(gamesExpecteds))

        runBlocking {
            val result = gameRepository.getGamesByRound(1)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByRound_ResultSucess_VerifyRoomCall() {
        val gamesExpecteds = GameResponseFactory.makeGameList(6)
        prepareScenario(getGamesByRoundReturn = BaseResultResponse(gamesExpecteds))

        runBlocking {
            gameRepository.getGamesByRound(1)
            coVerify(exactly = 1) {
                gameDao.getGamesByRound(1)
            }
        }
    }

    @Test
    fun getGameByRound_ResultSucess_EmptyList() {
        prepareScenario(getGamesByRoundReturn = BaseResultResponse(null))
        val expected = NetworkResponse.Success(listOf<GameResponse>())


        runBlocking {
            val call = gameRepository.getGamesByRound(1)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Success)
            assertEquals(expected, result)
        }
    }

    @Test
    fun getGameByRound_ResultError() {
        prepareErrorScenario()
        val expected = NetworkResponse.Error(NetworkError("msg", null))

        runBlocking {
            val call = gameRepository.getGamesByRound(2)
            val result = call.mapError { it }
            assert(result is NetworkResponse.Error)
            assertEquals(expected, result)
        }
    }

    private fun prepareErrorScenario(
        getGameDaoResult: GameEntity? = null,
        getGamesByRoundDaoResult: List<GameEntity> = listOf()
    ) {
        coEvery {
            gameDao.getGame(any())
        } returns getGameDaoResult
        coEvery {
            gameDao.getGamesByRound(any())
        } returns getGamesByRoundDaoResult
        coEvery {
            gameApi.getGamesByRound(any())
        } throws Exception("msg")
        coEvery {
            gameApi.getGamesByDay(any())
        } throws Exception("msg")
        coEvery {
            gameApi.getGameById(any())
        } throws Exception("msg")
    }

    private fun prepareScenario(
        getGamesReturn: BaseResultResponse<List<GameResponse>> = BaseResultResponse(listOf()),
        getGamesByRoundReturn: BaseResultResponse<List<GameResponse>> = BaseResultResponse(listOf()),
        getGameByTeam: BaseResultResponse<List<GameResponse>> = BaseResultResponse(listOf()),
        gamebyIdResult: BaseResultResponse<GameResponse> =
            BaseResultResponse(GameResponseFactory.makeGameResponse()),
        getGameByDate: BaseResultResponse<List<GameResponse>> = BaseResultResponse(listOf()),
        getGameDaoResult: GameEntity? = null,
        getGamesByRoundDaoResult: List<GameEntity> = listOf(),
        gameResponseMapperResult: GameResponse = GameResponseFactory.makeGameResponse(),
        gameEntityMapperResult: GameEntity = GameEntityFactory.makeGameEntity(),
    ) {
        every {
            gameEntityMapper.mapFrom(any())
        } returns gameEntityMapperResult
        every {
            gameResponseMapper.mapFrom(any())
        } returns gameResponseMapperResult
        coEvery {
            gameDao.getGame(any())
        } returns getGameDaoResult
        coEvery {
            gameDao.insertAll(any())
        } just runs
        coEvery {
            gameDao.getGamesByRound(any())
        } returns getGamesByRoundDaoResult
        coEvery {
            gameApi.getGames()
        } returns getGamesReturn
        coEvery {
            gameApi.getGamesByRound(any())
        } returns getGamesByRoundReturn
        coEvery {
            gameApi.getGameById(any())
        } returns gamebyIdResult
        coEvery {
            gameApi.getGamesByTeam(any())
        } returns getGameByTeam
        coEvery {
            gameApi.getGamesByDay(any())
        } returns getGameByDate
    }
}