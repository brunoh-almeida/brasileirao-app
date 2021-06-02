package br.com.brasileiraoapp.viewmodel.detail

import androidx.lifecycle.viewModelScope
import br.com.brasileiraoapp.presentation.mapper.BidResultToBidModelMapper
import br.com.brasileiraoapp.usecase.GetBidsOfGameUseCase
import br.com.brasileiraoapp.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

class GameDetailViewModel(
    override val viewState: GameDetailViewState,
    private val bidsOfGameUseCase: GetBidsOfGameUseCase,
    private val bidMapper: BidResultToBidModelMapper
) : BaseViewModel<GameDetailViewState, GameDetailViewAction>() {

    override fun dispatchViewAction(viewAction: GameDetailViewAction) {
        when (viewAction) {
            is GameDetailViewAction.GetBids -> getBidsOfGame(viewAction.id)
        }
    }

    private fun getBidsOfGame(id: Long) {
        viewState.loading.postValue(true)
        viewModelScope.launch {
            bidsOfGameUseCase(id).onSuccess { resultList ->
                val bidList = resultList.map {
                    bidMapper.mapFrom(it)
                }
                viewState.gameBids.postValue(bidList)
                viewState.loading.postValue(false)
            }.onError {
                viewState.loading.postValue(false)
            }
        }
    }
}