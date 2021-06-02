package br.com.brasileiraoapp.viewmodel.list

import android.os.Parcelable
import androidx.lifecycle.MutableLiveData
import br.com.brasileiraoapp.presentation.model.GameModel
import br.com.brasileiraoapp.presentation.model.GameViewResult
import br.com.brasileiraoapp.usecase.ResultError
import br.com.brasileiraoapp.viewmodel.SingleLiveEvent

class GameViewState {
    val gameResult = SingleLiveEvent<GameViewResult?>()
    val shouldShowResult = MutableLiveData<Boolean>()
    val shouldShowLoading = MutableLiveData<Boolean>()
    var round = MutableLiveData(1)
    val error = SingleLiveEvent<ResultError?>()
    var listState: Parcelable? = null

    val action = SingleLiveEvent<Action>()
    sealed class Action {
        object SwipeLoadFinished : Action()
        data class OpenGameDetail(val game: GameModel) : Action()
    }
}