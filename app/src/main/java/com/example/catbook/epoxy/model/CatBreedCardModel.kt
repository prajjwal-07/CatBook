package com.example.catbook.epoxy.model

import android.view.View
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.example.catbook.R
import com.example.catbook.data.local.CatBreedData
import com.example.catbook.databinding.CatBreedCardBinding

@EpoxyModelClass
abstract class CatBreedCardModel : EpoxyModelWithHolder<CatBreedCardModel.Holder>() {

    @EpoxyAttribute
    var data: CatBreedData? = null

    @EpoxyAttribute
    var catBreedSelected: Boolean = false

    @EpoxyAttribute(EpoxyAttribute.Option.DoNotHash)
    var onClick: ((Boolean) -> Unit)? = null

    override fun bind(holder: Holder) {
        data?.let { catBreedData ->
            holder.binding.apply {
                breedName.text = catBreedData.breedName
                location.text = catBreedData.origin
                subTitle.text = catBreedData.temperament
                checkBtn.isChecked = catBreedSelected
                checkBtn.setOnClickListener {
                    onClick?.invoke(catBreedSelected)
                }
            }
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var binding: CatBreedCardBinding
        override fun bindView(itemView: View) {
            binding = CatBreedCardBinding.bind(itemView)
        }
    }

    override fun getDefaultLayout() = R.layout.cat_breed_card
}