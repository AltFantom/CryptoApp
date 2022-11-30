package com.kupriyanov.cryptoapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kupriyanov.cryptoapp.R
import com.kupriyanov.cryptoapp.adapters.CoinInfoAdapter
import com.kupriyanov.cryptoapp.pojo.CoinPriceInfo
import com.kupriyanov.cryptoapp.viewModels.CoinViewModel
import kotlinx.android.synthetic.main.activity_coin_price_list.*

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinPriceInfo: CoinPriceInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinPriceInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[CoinViewModel::class.java]
        viewModel.priceList.observe(this) {
            adapter.coinInfoList = it
        }
    }
}