package com.emirkanmaz.cryptotracker.crypto.presentation.coin_detail

import com.emirkanmaz.cryptotracker.crypto.presentation.models.DisplayableNumber
import java.text.NumberFormat
import java.util.Locale

data class ValueLabel(
    val value: Float,
    val unit: String,

){

    fun formatted(): String {
        val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
            val fractionDigits = when {
                (value > 1000F) -> 0
                (value in 2F .. 999F) -> 2
                else -> 3
            }
            maximumFractionDigits = fractionDigits
            minimumFractionDigits = fractionDigits

        }
        return "${formatter.format(value)}$unit"
    }

}
