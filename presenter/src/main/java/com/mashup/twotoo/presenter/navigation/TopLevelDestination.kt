package com.mashup.twotoo.presenter.navigation

import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.icon.Icon
import com.mashup.twotoo.presenter.designsystem.icon.Icon.DrawableResourceIcon
import com.mashup.twotoo.presenter.designsystem.icon.TwoTooIcon

enum class TopLevelDestination(
    val icon: Icon,
    val iconTextId: Int,
    val buttonTitleTextId: Int,
) {
    Garden(
        icon = DrawableResourceIcon(TwoTooIcon.GardenIcon),
        iconTextId = R.string.garden,
        buttonTitleTextId = R.string.garden_nav_button,
    ),
    Home(
        icon = DrawableResourceIcon(TwoTooIcon.HomeIcon),
        iconTextId = R.string.home,
        buttonTitleTextId = R.string.home_nav_button,
    ),
    User(
        icon = DrawableResourceIcon(TwoTooIcon.UserIcon),
        iconTextId = R.string.user,
        buttonTitleTextId = R.string.user_nav_button,
    ),
}
