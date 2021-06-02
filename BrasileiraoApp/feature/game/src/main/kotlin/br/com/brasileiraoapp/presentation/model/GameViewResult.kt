package br.com.brasileiraoapp.presentation.model

sealed class GameViewResult {
    data class Success(val content: List<GameModel>) : GameViewResult()
    class Error(val message: String) : GameViewResult()
}