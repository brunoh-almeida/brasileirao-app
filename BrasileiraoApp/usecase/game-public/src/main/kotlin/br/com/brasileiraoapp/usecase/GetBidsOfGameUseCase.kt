package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.result.BidResult

interface GetBidsOfGameUseCase {
    suspend operator fun invoke(id: Long): Result<List<BidResult>, ResultError>
}