package br.com.brasileiraoapp.repository

import br.com.brasileiraoapp.api.GameApi
import br.com.brasileiraoapp.database.dao.GameDao
import br.com.brasileiraoapp.mapper.GameEntityToGameResponseMapper
import br.com.brasileiraoapp.mapper.GameResponseToGameEntityMapper
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import br.com.brasileiraoapp.network.response.ZeroResultException
import br.com.brasileiraoapp.network.safeApiCall
import repository.GameRepository
import response.GameResponse

class GameRepositoryImpl(
    private val gameApi: GameApi,
    private val gameDao: GameDao,
    private val gameResponseMapper: GameEntityToGameResponseMapper,
    private val gameEntityMapper: GameResponseToGameEntityMapper
) : GameRepository {

    override suspend fun getGamesById(gameId: Long):
            NetworkResponse<GameResponse, NetworkError> {
        gameDao.getGame(gameId)?.let {
            return NetworkResponse.Success(gameResponseMapper.mapFrom(it))
        } ?: run {
            return safeApiCall {
                gameApi.getGameById(gameId).content ?: throw ZeroResultException()
            }
        }
    }

    override suspend fun getGamesByRound(
        round: Int
    ): NetworkResponse<List<GameResponse>, NetworkError> {
        val gamesEntities = gameDao.getGamesByRound(round)
        return if (!gamesEntities.isNullOrEmpty()) {
            NetworkResponse.Success(
                gamesEntities.map { gameResponseMapper.mapFrom(it) }
            )
        } else {
            safeApiCall {
                val games = gameApi.getGamesByRound(round).content
                games?.forEach {
                    gameDao.insertAll(gameEntityMapper.mapFrom(it))
                }
                games ?: listOf()
            }
        }
    }

    override suspend fun getGames(): NetworkResponse<List<GameResponse>, NetworkError> {
        return safeApiCall {
            gameApi.getGames().content ?: listOf()
        }
    }

    override suspend fun getGamesByDay(day: String): NetworkResponse<List<GameResponse>, NetworkError> {
        return safeApiCall {
            gameApi.getGamesByDay(day).content ?: listOf()
        }
    }

    override suspend fun getGamesByTeam(teamId: Long):
            NetworkResponse<List<GameResponse>, NetworkError> {
        return safeApiCall {
            gameApi.getGamesByTeam(teamId).content ?: listOf()
        }
    }
}