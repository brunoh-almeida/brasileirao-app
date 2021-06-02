package br.com.brasileiraoapp.repository

import br.com.brasileiraoapp.api.BidApi
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import br.com.brasileiraoapp.network.safeApiCall
import repository.BidRepository
import response.BidResponse

class BidRepositoryImpl(private val bidApi: BidApi): BidRepository {
    override suspend fun getBidsOfGame(id: Long): NetworkResponse<List<BidResponse>, NetworkError> {
       return safeApiCall {
            bidApi.getBidsOfGame(id)
        }
    }
}