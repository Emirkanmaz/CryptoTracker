package com.emirkanmaz.cryptotracker.crypto.presentation.coin_list

import com.emirkanmaz.cryptotracker.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
    data object OnRefresh: CoinListAction
}
