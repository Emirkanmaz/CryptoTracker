package com.emirkanmaz.cryptotracker.crypto.presentation.models

import android.icu.number.FormattedNumber
import androidx.annotation.DrawableRes

data class CoinUi (
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int
)

data class DisplayableNumber(
    val value: Double,
    val formatted: FormattedNumber
)