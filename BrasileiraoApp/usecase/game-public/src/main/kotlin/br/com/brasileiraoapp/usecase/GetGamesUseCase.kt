package br.com.brasileiraoapp.usecase

import br.com.brasileiraoapp.result.GameResult

interface GetGamesUseCase {
    suspend operator fun invoke(): Result<List<GameResult>, ResultError>
}