package br.com.brasileiraoapp.di

import br.com.brasileiraoapp.api.BidApi
import br.com.brasileiraoapp.api.GameApi
import br.com.brasileiraoapp.mapper.GameEntityToGameResponseMapper
import br.com.brasileiraoapp.mapper.GameResponseToGameEntityMapper
import br.com.brasileiraoapp.repository.BidRepositoryImpl
import br.com.brasileiraoapp.repository.GameRepositoryImpl
import org.koin.dsl.module
import repository.BidRepository
import repository.GameRepository
import retrofit2.Retrofit

val scoreApiModule = module {
    single { get<Retrofit>().create(GameApi::class.java) }
}

val scoreRepositoryModule = module {
    factory<GameRepository> { GameRepositoryImpl(get(), get(), get(), get()) }
    single { GameResponseToGameEntityMapper() }
    single { GameEntityToGameResponseMapper() }
}

val bidApiModule = module {
    single { get<Retrofit>().create(BidApi::class.java) }
}

val bidRepositoryModule = module {
    single<BidRepository> { BidRepositoryImpl(get()) }
}
