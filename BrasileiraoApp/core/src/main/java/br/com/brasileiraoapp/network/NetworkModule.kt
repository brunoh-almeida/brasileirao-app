package br.com.brasileiraoapp.network

import br.com.brasileiraoapp.network.RetrofitProvider.buildOkHttpClient
import br.com.brasileiraoapp.network.RetrofitProvider.buildRetrofitClient
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val networkModule = module {

    single {
        buildRetrofitClient(
            buildOkHttpClient(),
            RetrofitProvider.NetworkConstants.BASE_URL
        )
    }
}

fun loadNetworkModule() = load

private val load by lazy {
    loadKoinModules(networkModule)
}