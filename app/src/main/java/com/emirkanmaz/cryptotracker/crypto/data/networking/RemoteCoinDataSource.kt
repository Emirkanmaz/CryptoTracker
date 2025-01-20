package com.emirkanmaz.cryptotracker.crypto.data.networking

import com.emirkanmaz.cryptotracker.core.data.networking.constructUrl
import com.emirkanmaz.cryptotracker.core.data.networking.safeCall
import com.emirkanmaz.cryptotracker.core.domain.util.NetworkError
import com.emirkanmaz.cryptotracker.core.domain.util.Result
import com.emirkanmaz.cryptotracker.core.domain.util.map
import com.emirkanmaz.cryptotracker.crypto.data.mappers.toCoin
import com.emirkanmaz.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.emirkanmaz.cryptotracker.crypto.domain.Coin
import com.emirkanmaz.cryptotracker.crypto.domain.CoinDataSource
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteCoinDataSource(
    private val httpClient: HttpClient,
) : CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(constructUrl("/assets"))
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }


}