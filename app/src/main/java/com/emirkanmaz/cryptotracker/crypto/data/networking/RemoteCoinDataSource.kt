package com.emirkanmaz.cryptotracker.crypto.data.networking

import com.emirkanmaz.cryptotracker.core.data.networking.constructUrl
import com.emirkanmaz.cryptotracker.core.data.networking.safeCall
import com.emirkanmaz.cryptotracker.core.domain.util.NetworkError
import com.emirkanmaz.cryptotracker.core.domain.util.Result
import com.emirkanmaz.cryptotracker.core.domain.util.map
import com.emirkanmaz.cryptotracker.crypto.data.mappers.toCoin
import com.emirkanmaz.cryptotracker.crypto.data.mappers.toCoinPrice
import com.emirkanmaz.cryptotracker.crypto.data.networking.dto.CoinHistoryDto
import com.emirkanmaz.cryptotracker.crypto.data.networking.dto.CoinsResponseDto
import com.emirkanmaz.cryptotracker.crypto.domain.Coin
import com.emirkanmaz.cryptotracker.crypto.domain.CoinDataSource
import com.emirkanmaz.cryptotracker.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

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

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime,
    ): Result<List<CoinPrice>, NetworkError> {
        val startMilis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        val endMilis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()

        return safeCall<CoinHistoryDto> {
            httpClient.get(constructUrl("/assets/$coinId/history")) {
                parameter("interval", "h6")
                parameter("start", startMilis)
                parameter("end", endMilis)
            }
        }.map { response ->
            response.data.map { it.toCoinPrice() }
        }
    }

}