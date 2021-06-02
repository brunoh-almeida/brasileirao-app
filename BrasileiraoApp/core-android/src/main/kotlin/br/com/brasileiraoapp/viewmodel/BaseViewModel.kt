package br.com.brasileiraoapp.viewmodel

import androidx.lifecycle.ViewModel

abstract class BaseViewModel<VS, A> : ViewModel() {

    abstract val viewState: VS
    abstract fun dispatchViewAction(viewAction: A)
}