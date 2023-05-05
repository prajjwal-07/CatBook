package com.example.catbook.epoxy.model

import android.view.View
import android.widget.ImageView
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import com.bumptech.glide.Glide
import com.example.catbook.R
import com.example.catbook.data.local.CatImageData
import com.example.catbook.databinding.CatImageCardBinding


@EpoxyModelClass
abstract class CatImageCardModel : EpoxyModelWithHolder<CatImageCardModel.Holder>() {

    @EpoxyAttribute
    var data: CatImageData? = null

    override fun bind(holder: Holder) {
        data?.let { catImageDate ->
            holder.binding.apply {
                title.text = catImageDate.breedName
                setImage(holder.binding.image, catImageDate.imageUrl)
            }
        }
    }

    class Holder : EpoxyHolder() {
        lateinit var binding: CatImageCardBinding
        override fun bindView(itemView: View) {
            binding = CatImageCardBinding.bind(itemView)
        }
    }

    override fun getDefaultLayout() = R.layout.cat_image_card

    private fun setImage(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}