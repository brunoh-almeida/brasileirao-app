package repository

import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import response.BidResponse

interface BidRepository {
    suspend fun getBidsOfGame(id: Long): NetworkResponse<List<BidResponse>, NetworkError>
}