package com.kupriyanov.cryptoapp.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kupriyanov.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo
import com.kupriyanov.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.kupriyanov.cryptoapp.presentation.viewModels.CoinViewModel

class CoinPriceListActivity : AppCompatActivity() {

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this)[CoinViewModel::class.java]
    }

    private val binding: ActivityCoinPriceListBinding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfo.fromSymbol
                )
                startActivity(intent)
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }
}