package com.kupriyanov.cryptoapp.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kupriyanov.cryptoapp.R
import com.kupriyanov.cryptoapp.databinding.ActivityCoinPriceListBinding
import com.kupriyanov.cryptoapp.domain.entities.CoinInfo
import com.kupriyanov.cryptoapp.presentation.CoinApp
import com.kupriyanov.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.kupriyanov.cryptoapp.presentation.viewModels.CoinViewModel
import com.kupriyanov.cryptoapp.presentation.viewModels.ViewModelFactory
import javax.inject.Inject

class CoinPriceListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoinViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[CoinViewModel::class.java]
    }

    private val binding: ActivityCoinPriceListBinding by lazy {
        ActivityCoinPriceListBinding.inflate(layoutInflater)
    }

    private val component by lazy {
        (application as CoinApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfo: CoinInfo) {
                if (isOnePaneMode()) {
                    launchDetailActivity(coinInfo.fromSymbol)
                } else {
                    launchDetailFragment(coinInfo.fromSymbol)
                }
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        binding.rvCoinPriceList.itemAnimator = null
        viewModel.coinInfoList.observe(this) {
            adapter.submitList(it)
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(fromSymbol: String) {
        val intent = CoinDetailActivity.newIntent(
            this@CoinPriceListActivity,
            fromSymbol
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(fromSymbol: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, CoinDetailFragment.newInstance(fromSymbol))
            .addToBackStack(null)
            .commit()
    }
}