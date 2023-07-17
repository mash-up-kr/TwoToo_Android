package com.mashup.twotoo.presenter.garden

sealed interface GardenSideEffect {
    data class Toast(val text: String) : GardenSideEffect
}
