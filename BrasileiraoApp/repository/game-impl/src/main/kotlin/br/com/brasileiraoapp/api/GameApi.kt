package br.com.brasileiraoapp.api

import br.com.brasileiraoapp.network.response.BaseResultResponse
import response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GameApi {

    @GET("games/")
    suspend fun getGames(): BaseResultResponse<List<GameResponse>>

    @GET("games/byRound")
    suspend fun getGamesByRound(@Query("round") round: Int): BaseResultResponse<List<GameResponse>>

    @GET("games/byTeam")
    suspend fun getGamesByTeam(@Query("teamId") teamId: Long): BaseResultResponse<List<GameResponse>>

    @GET("games/byDay")
    suspend fun getGamesByDay(@Query("date") date: String): BaseResultResponse<List<GameResponse>>

    @GET("games/byId")
    suspend fun getGameById(@Query("gameId") gameId: Long): BaseResultResponse<GameResponse>

}