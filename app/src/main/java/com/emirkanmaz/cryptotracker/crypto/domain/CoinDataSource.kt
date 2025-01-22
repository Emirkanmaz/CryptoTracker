package com.emirkanmaz.cryptotracker.crypto.domain

import com.emirkanmaz.cryptotracker.core.domain.util.NetworkError
import com.emirkanmaz.cryptotracker.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {

    suspend fun getCoins(): Result<List<Coin>, NetworkError>

    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError>
}