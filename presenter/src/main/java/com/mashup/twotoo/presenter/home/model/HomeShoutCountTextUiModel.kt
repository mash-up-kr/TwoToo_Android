package com.mashup.twotoo.presenter.home.model

/**
 * @Created by 김현국 2023/06/09
 */

data class HomeShotCountTextUiModel(
    val count: Int,
) {
    companion object {
        val default = HomeShotCountTextUiModel(
            count = 4,
        )
    }
}
