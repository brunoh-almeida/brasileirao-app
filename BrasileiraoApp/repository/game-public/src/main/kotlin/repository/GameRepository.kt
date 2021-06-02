package repository

import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import response.GameResponse

interface GameRepository {

    suspend fun getGamesById(gameId: Long):
            NetworkResponse<GameResponse, NetworkError>

    suspend fun getGamesByRound(round: Int):
            NetworkResponse<List<GameResponse>, NetworkError>

    suspend fun getGames(): NetworkResponse<List<GameResponse>, NetworkError>

    suspend fun getGamesByDay(day: String): NetworkResponse<List<GameResponse>, NetworkError>

    suspend fun getGamesByTeam(teamId: Long): NetworkResponse<List<GameResponse>, NetworkError>

}