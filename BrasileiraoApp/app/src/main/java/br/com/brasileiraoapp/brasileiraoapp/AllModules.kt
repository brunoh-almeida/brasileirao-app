package br.com.brasileiraoapp.brasileiraoapp

import br.com.brasileiraoapp.database.di.databaseModule
import br.com.brasileiraoapp.di.*
import br.com.brasileiraoapp.network.networkModule
import br.com.brasileiraoapp.presentation.di.gameViewModelModule
import br.com.brasileiraoapp.presentation.di.mapperModule

fun allModules() = listOf(
    networkModule,
    scoreApiModule,
    scoreRepositoryModule,
    gameMapperModule,
    gameUseCaseModule,
    gameViewModelModule,
    mapperModule,
    bidApiModule,
    bidRepositoryModule,
    databaseModule
)