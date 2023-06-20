package com.mashup.twotoo.presenter.home.model.flower

import androidx.compose.ui.unit.dp
import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.home.model.UserType
import com.mashup.twotoo.presenter.home.model.UserType.ME
import com.mashup.twotoo.presenter.home.model.UserType.PARTNER
import com.mashup.twotoo.presenter.home.model.flower.Stage.Bloom
import com.mashup.twotoo.presenter.home.model.flower.Stage.Bud
import com.mashup.twotoo.presenter.home.model.flower.Stage.Flower
import com.mashup.twotoo.presenter.home.model.flower.Stage.Seed
import com.mashup.twotoo.presenter.home.model.flower.Stage.Shoot

/**
 * @Created by 김현국 2023/06/20
 */
data class Rose(val userType: UserType, val growType: Stage) : FlowerType(
    name = FlowerName.Rose,
) {
    override val flowerImage: FlowerImage = when (userType) {
        ME -> {
            when (growType) {
                Seed -> FlowerImage(image = R.drawable.img_home_seed_me, width = 53.dp, height = 49.dp)
                Shoot -> FlowerImage(image = R.drawable.img_home_shoot_me, width = 61.dp, height = 69.dp)
                Bud -> FlowerImage(image = R.drawable.img_home_bud_me, width = 77.dp, height = 117.dp)
                Flower -> FlowerImage(image = R.drawable.img_home_flower_rose_me, width = 127.dp, height = 211.dp)
                Bloom -> FlowerImage(image = R.drawable.img_home_bloom_rose_me, width = 127.dp, height = 211.dp)
            }
        }
        PARTNER -> {
            when (growType) {
                Seed -> FlowerImage(image = R.drawable.img_home_seed_partner, width = 53.dp, height = 49.dp)
                Shoot -> FlowerImage(image = R.drawable.img_home_shoot_partner, width = 61.dp, height = 69.dp)
                Bud -> FlowerImage(image = R.drawable.img_home_bud_partner, width = 77.dp, height = 117.dp)
                Flower -> FlowerImage(image = R.drawable.img_home_flower_rose_partner, width = 127.dp, height = 211.dp)
                Bloom -> FlowerImage(image = R.drawable.img_home_bloom_rose_partner, width = 127.dp, height = 211.dp)
            }
        }
    }
}
