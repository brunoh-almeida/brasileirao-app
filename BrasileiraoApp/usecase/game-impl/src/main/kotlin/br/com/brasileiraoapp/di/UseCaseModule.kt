package br.com.brasileiraoapp.di

import br.com.brasileiraoapp.mapper.BidResponseToBidResultMapper
import br.com.brasileiraoapp.mapper.GameResponseToGameResultMapper
import br.com.brasileiraoapp.mapper.TeamResponseToTeamResultMapper
import br.com.brasileiraoapp.usecase.*
import org.koin.dsl.module

val gameUseCaseModule = module {
    single<GetGamesUseCase> {
        GetGamesUseCaseImpl(get(), get())
    }

    single<GetBidsOfGameUseCase> {
        GetBidsOfGameUseCaseImpl(get(), get())
    }

    single<GetGamesByRoundUseCase> {
        GetGamesByRoundUseCaseImpl(get(), get())
    }
}

val gameMapperModule = module {
    single {
        GameResponseToGameResultMapper(get())
    }
    single {
        TeamResponseToTeamResultMapper()
    }
    single {
        BidResponseToBidResultMapper()
    }
}
