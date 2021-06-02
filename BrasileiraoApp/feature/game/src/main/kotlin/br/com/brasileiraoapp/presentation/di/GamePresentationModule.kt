package br.com.brasileiraoapp.presentation.di

import br.com.brasileiraoapp.presentation.mapper.BidResultToBidModelMapper
import br.com.brasileiraoapp.presentation.mapper.GameResultToGameMapper
import br.com.brasileiraoapp.presentation.mapper.TeamResultToTeamMapper
import br.com.brasileiraoapp.viewmodel.detail.GameDetailViewModel
import br.com.brasileiraoapp.viewmodel.detail.GameDetailViewState
import br.com.brasileiraoapp.viewmodel.list.GameViewModel
import br.com.brasileiraoapp.viewmodel.list.GameViewState
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val gameViewModelModule = module {
    viewModel { GameViewModel(get(), get(), get()) }
    single { GameViewState() }
    viewModel { GameDetailViewModel(get(), get(), get()) }
    single { GameDetailViewState() }
}

val mapperModule = module {
    single { GameResultToGameMapper(get()) }
    single { TeamResultToTeamMapper() }
    single { BidResultToBidModelMapper() }
}