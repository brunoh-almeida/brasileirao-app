package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.mapper.GameResponseToGameResultMapper
import br.com.brasileiraoapp.network.response.NetworkError
import br.com.brasileiraoapp.network.response.NetworkResponse
import repository.GameRepository
import response.GameResponse
import br.com.brasileiraoapp.result.GameResult

class GetGamesByRoundUseCaseImpl(
    private val repository: GameRepository,
    private val gameResultMapper: GameResponseToGameResultMapper
) : GetGamesByRoundUseCase {

    override suspend fun invoke(round: Int): Result<List<GameResult>, ResultError> {
        return when (val result = repository.getGamesByRound(round)) {
            is NetworkResponse.Success -> handleSuccess(result)
            is NetworkResponse.Error -> handleError(result)
        }
    }

    private fun handleSuccess(result: NetworkResponse.Success<List<GameResponse>>):
            Result<List<GameResult>, ResultError> {
        return Result.Success(result.value.map {
            gameResultMapper.mapFrom(it)
        })
    }

    private fun handleError(result: NetworkResponse.Error<NetworkError>):
            Result<List<GameResult>, ResultError> {
        return Result.Error(ResultError.UnknownError())
    }
}
