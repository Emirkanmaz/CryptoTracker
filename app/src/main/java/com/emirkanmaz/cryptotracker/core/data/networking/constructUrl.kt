package com.emirkanmaz.cryptotracker.core.data.networking

import com.emirkanmaz.cryptotracker.BuildConfig

fun constructUrl(url: String): String {
    return when{
        url.contains(BuildConfig.BASE_URL) -> url
        url.startsWith("/") -> BuildConfig.BASE_URL + url
        else -> BuildConfig.BASE_URL + "/" + url
    }
}