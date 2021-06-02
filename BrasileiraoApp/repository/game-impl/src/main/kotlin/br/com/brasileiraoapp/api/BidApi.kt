package br.com.brasileiraoapp.api

import response.BidResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BidApi {
    @GET("games/bids")
    suspend fun getBidsOfGame(@Query("gameId") gameId: Long): List<BidResponse>
}