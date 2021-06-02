package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.result.GameResult

interface GetGamesByRoundUseCase {
    suspend operator fun invoke(round: Int): Result<List<GameResult>, ResultError>
}
