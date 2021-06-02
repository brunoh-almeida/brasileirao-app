package br.com.brasileiraoapp.viewmodel.list

import br.com.brasileiraoapp.presentation.model.GameModel

sealed class GameViewAction {
    object Init: GameViewAction()
    data class OnRoundClick(val round: Int): GameViewAction()
    data class OnGameListClick(val game: GameModel) : GameViewAction()
    object OnSwipeRefresh: GameViewAction()
}