package com.emirkanmaz.cryptotracker.core.data.networking

import android.util.Log
import com.emirkanmaz.cryptotracker.core.domain.util.NetworkError
import com.emirkanmaz.cryptotracker.core.domain.util.Result
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse,
): Result<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.Error(NetworkError.NO_INTERNET_CONNECTION)
    } catch (e: SerializationException) {
        Log.d("error", e.message.toString())
        return Result.Error(NetworkError.SERIALIZATION_ERROR)
    } catch (e: Exception) {
        coroutineContext.ensureActive() // for coroutine cancellation exception
        return Result.Error(NetworkError.UNKNOWN_ERROR)
    }
    return responseToResult(response)

}
