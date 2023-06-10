package com.mashup.twotoo.presenter.navigation

import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.icon.Icon
import com.mashup.twotoo.presenter.designsystem.icon.Icon.DrawableResourceIcon
import com.mashup.twotoo.presenter.designsystem.icon.TwoTooIcon

enum class TopLevelDestination(
    val icon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    Garden(
        icon = DrawableResourceIcon(TwoTooIcon.GardenIcon),
        iconTextId = R.string.garden,
        titleTextId = R.string.garden,
    ),
    Home(
        icon = DrawableResourceIcon(TwoTooIcon.HomeIcon),
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    User(
        icon = DrawableResourceIcon(TwoTooIcon.UserIcon),
        iconTextId = R.string.user,
        titleTextId = R.string.user,
    ),
}
