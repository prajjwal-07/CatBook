package com.example.catbook.interfaces

interface CatHomeListener {
    fun <T> onClicked(type: ClickType, data: T? = null, index: Int? = 0)
}

enum class ClickType {
    CAT_BREED_CARD_CLICK,
    SEE_CAT_IMAGE_BUTTON_CLICK
}