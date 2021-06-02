package br.com.brasileiraoapp.viewmodel.detail

sealed class GameDetailViewAction {
    data class GetBids(val id: Long): GameDetailViewAction()
}