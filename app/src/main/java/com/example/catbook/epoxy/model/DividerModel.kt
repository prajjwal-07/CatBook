package com.example.catbook.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.catbook.R
import com.example.catbook.databinding.DividerBinding

@EpoxyModelClass
abstract class DividerModel : EpoxyModelWithHolder<DividerModel.Holder>() {

    class Holder : EpoxyHolder() {
        lateinit var binding: DividerBinding
        override fun bindView(itemView: View) {
            binding = DividerBinding.bind(itemView)
        }
    }

    override fun getDefaultLayout() = R.layout.divider
}