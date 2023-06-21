package com.mashup.twotoo.presenter.home.model.flower

import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER
import com.mashup.twotoo.presenter.home.model.flower.Stage.Fifth
import com.mashup.twotoo.presenter.home.model.flower.Stage.Second
import com.mashup.twotoo.presenter.home.model.flower.Stage.Fourth
import com.mashup.twotoo.presenter.home.model.flower.Stage.Third
import com.mashup.twotoo.presenter.home.model.flower.Stage.Zero
import com.mashup.twotoo.presenter.home.model.flower.Stage.First

/**
 * @Created by 김현국 2023/06/20
 */
data class Tulip(val userType: UserType, val growType: Stage) : FlowerType(
    name = FlowerName.Tulip,
) {
    override val flowerImage: FlowerImage = when (userType) {
        ME -> {
            when (growType) {
                Zero -> FlowerImage(image = R.drawable.img_home_zero_stage_me, width = 53.dp, height = 49.dp)
                First -> FlowerImage(image = R.drawable.img_home_first_stage_me, width = 61.dp, height = 69.dp)
                Second -> FlowerImage(image = R.drawable.img_home_second_stage_me, width = 77.dp, height = 117.dp)
                Third -> FlowerImage(image = R.drawable.img_home_third_stage_me, width = 102.dp, height = 179.dp)
                Fourth -> FlowerImage(image = R.drawable.img_home_fourth_stage_tulip_me, width = 127.dp, height = 211.dp)
                Fifth -> FlowerImage(image = R.drawable.img_home_fifth_stage_tulip_me, width = 127.dp, height = 211.dp)
            }
        }
        PARTNER -> {
            when (growType) {
                Zero -> FlowerImage(image = R.drawable.img_home_zero_stage_partner, width = 53.dp, height = 49.dp)
                First -> FlowerImage(image = R.drawable.img_home_first_stage_partner, width = 61.dp, height = 69.dp)
                Second -> FlowerImage(image = R.drawable.img_home_second_stage_partner, width = 77.dp, height = 117.dp)
                Third -> FlowerImage(image = R.drawable.img_home_third_stage_me, width = 102.dp, height = 179.dp)
                Fourth -> FlowerImage(image = R.drawable.img_home_fourth_stage_tulip_partner, width = 127.dp, height = 211.dp)
                Fifth -> FlowerImage(image = R.drawable.img_home_fifth_stage_tulip_partner, width = 127.dp, height = 211.dp)
            }
        }
    }
}
