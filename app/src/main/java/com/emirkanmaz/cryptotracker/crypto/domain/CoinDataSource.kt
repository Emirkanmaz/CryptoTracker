package com.emirkanmaz.cryptotracker.crypto.domain

import com.emirkanmaz.cryptotracker.core.domain.util.NetworkError
import com.emirkanmaz.cryptotracker.core.domain.util.Result

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
}