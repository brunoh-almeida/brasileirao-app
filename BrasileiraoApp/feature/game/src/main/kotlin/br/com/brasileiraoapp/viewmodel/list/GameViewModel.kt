package br.com.brasileiraoapp.viewmodel.list

import androidx.lifecycle.viewModelScope
import br.com.brasileiraoapp.presentation.mapper.GameResultToGameMapper
import br.com.brasileiraoapp.presentation.model.GameModel
import br.com.brasileiraoapp.presentation.model.GameViewResult
import br.com.brasileiraoapp.usecase.GetGamesByRoundUseCase
import br.com.brasileiraoapp.usecase.ResultError
import br.com.brasileiraoapp.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class GameViewModel(
    override val viewState: GameViewState,
    private val getGamesByRoundUseCase: GetGamesByRoundUseCase,
    private val gameMapper: GameResultToGameMapper
) : BaseViewModel<GameViewState, GameViewAction>() {

    override fun dispatchViewAction(viewAction: GameViewAction) {
        when (viewAction) {
            is GameViewAction.Init -> getGamesByRound(viewState.round.value ?: 1)
            is GameViewAction.OnRoundClick -> getGamesByRound(viewAction.round)
            is GameViewAction.OnGameListClick -> onGameListClick(viewAction.game)
            is GameViewAction.OnSwipeRefresh -> onSwipeRefresh()
        }
    }

    private fun onSwipeRefresh() {
        viewModelScope.launch {
            fetchResult(viewState.round.value ?: 1) {
                viewState.action.postValue(GameViewState.Action.SwipeLoadFinished)
            }
        }
    }

    private fun onGameListClick(game: GameModel) {
        viewState.action.postValue(GameViewState.Action.OpenGameDetail(game))
    }

    private fun getGamesByRound(round: Int) {
        viewModelScope.launch {
            showLoading()
            fetchResult(round) {
                hideLoading()
            }
        }
    }

    private suspend fun fetchResult(round: Int, finishAction: () -> Any) {
        getGamesByRoundUseCase(round).onSuccess { games ->
            val gameList = games.map {
                gameMapper.mapFrom(it)
            }
            viewState.gameResult.postValue(GameViewResult.Success(gameList))
            viewState.round.postValue(round)
            finishAction()
        }.onError {
            handleError(it)
            finishAction()
        }
    }

    private fun showLoading() {
        viewState.error.postValue(null)
        viewState.shouldShowLoading.postValue(true)
        viewState.shouldShowResult.postValue(false)
    }

    private fun hideLoading() {
        viewState.error.postValue(null)
        viewState.shouldShowLoading.postValue(false)
        viewState.shouldShowResult.postValue(true)
    }

    private fun handleError(it: ResultError) {
        viewState.error.postValue(it)
        viewState.shouldShowLoading.postValue(false)
        viewState.shouldShowResult.postValue(false)
    }
}