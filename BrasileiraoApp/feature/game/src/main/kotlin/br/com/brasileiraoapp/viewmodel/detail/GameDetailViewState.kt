package br.com.brasileiraoapp.viewmodel.detail

import br.com.brasileiraoapp.presentation.model.BidModel
import br.com.brasileiraoapp.viewmodel.SingleLiveEvent

class GameDetailViewState {
    val gameBids = SingleLiveEvent<List<BidModel>?>()
    val loading = SingleLiveEvent<Boolean>()
}