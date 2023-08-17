package com.app.batman.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.batman.R
import com.app.batman.databinding.RateCardViewBinding
import com.app.batman.models.Rate

class RateAdapter : RecyclerView.Adapter<RateAdapter.MainPageViewHolder>() {

    private var rates: List<Rate> = emptyList()
    private lateinit var rateCardViewBinding: RateCardViewBinding

    fun setRates(rates: List<Rate>?) {
        this.rates = rates!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        rateCardViewBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.rate_card_view, parent, false
            )
        return MainPageViewHolder(rateCardViewBinding)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
        val rate = rates[position]
        holder.bind(rate)
    }

    class MainPageViewHolder(
        private val rateCardViewBinding: RateCardViewBinding
    ) : RecyclerView.ViewHolder(rateCardViewBinding.root) {
        fun bind(rate: Rate) {
            rateCardViewBinding.rate = rate
            rateCardViewBinding.executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return rates.size
    }

}





