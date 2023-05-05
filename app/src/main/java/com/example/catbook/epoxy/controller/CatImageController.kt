package com.example.catbook.epoxy.controller

import com.airbnb.epoxy.AsyncEpoxyController
import com.example.catbook.data.local.CatBreedData
import com.example.catbook.data.local.CatImageData
import com.example.catbook.epoxy.model.CatImageCardModel_

class CatImageController : AsyncEpoxyController() {

    var data: List<CatImageData>? = null
        set(value) {
            field = value
            requestModelBuild()
        }

    override fun buildModels() {
        data?.forEachIndexed { index, catImageData ->
            CatImageCardModel_()
                .id("$index ${catImageData.id}")
                .data(catImageData)
                .addTo(this)
        }
    }
}