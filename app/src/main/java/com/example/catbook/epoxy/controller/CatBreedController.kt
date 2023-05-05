package com.example.catbook.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.example.catbook.data.local.CatBreedData
import com.example.catbook.epoxy.model.CatBreedCardModel_
import com.example.catbook.epoxy.model.DividerModel_
import com.example.catbook.interfaces.CatHomeListener
import com.example.catbook.interfaces.ClickType
import javax.inject.Inject

class CatBreedController @Inject constructor(
    private val listener: CatHomeListener
) : AsyncEpoxyController() {

    var data: List<CatBreedData>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    var selectedCatBreedId: List<String>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data?.forEachIndexed { index, catBreedData ->
            DividerModel_()
                .id("$index divider")
                .addTo(this)

            CatBreedCardModel_()
                .id("$index ${catBreedData.id}")
                .data(catBreedData)
                .catBreedSelected(selectedCatBreedId?.contains(catBreedData.id) ?: false)
                .onClick {
                    listener.onClicked(
                        type = ClickType.CAT_BREED_CARD_CLICK,
                        data = Pair(catBreedData.id, it)
                    )
                }
                .addTo(this)
        }
    }
}