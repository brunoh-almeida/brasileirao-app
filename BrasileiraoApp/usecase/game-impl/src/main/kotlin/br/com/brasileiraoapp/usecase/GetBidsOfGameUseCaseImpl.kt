package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.mapper.BidResponseToBidResultMapper
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import br.com.brasileiraoapp.result.BidResult
import repository.BidRepository
import response.BidResponse

class GetBidsOfGameUseCaseImpl(
    private val repository: BidRepository,
    private val bidResultMapper: BidResponseToBidResultMapper
) : GetBidsOfGameUseCase {
    override suspend fun invoke(id: Long): Result<List<BidResult>, ResultError> {
        return when (val result = repository.getBidsOfGame(id)) {
            is NetworkResponse.Success -> handleSuccess(result.value)
            is NetworkResponse.Error -> handleError(result.value)
        }
    }

    private fun handleError(value: NetworkError): Result<List<BidResult>, ResultError> {
        return Result.Error(ResultError.UnknownError(value.message))
    }

    private fun handleSuccess(value: List<BidResponse>): Result<List<BidResult>, ResultError> {
        return Result.Success(value.map {
            bidResultMapper.mapFrom(it)
        })
    }


}