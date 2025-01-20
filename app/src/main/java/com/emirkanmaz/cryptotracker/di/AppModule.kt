package com.emirkanmaz.cryptotracker.di

import com.emirkanmaz.cryptotracker.core.data.networking.HttpClientFactory
import com.emirkanmaz.cryptotracker.crypto.data.networking.RemoteCoinDataSource
import com.emirkanmaz.cryptotracker.crypto.domain.CoinDataSource
import com.emirkanmaz.cryptotracker.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    single{ HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)

}