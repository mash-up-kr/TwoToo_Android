package com.mashup.twotoo.presenter.navigation

import com.mashup.twotoo.presenter.R
import com.mashup.twotoo.presenter.designsystem.icon.Icon
import com.mashup.twotoo.presenter.designsystem.icon.Icon.DrawableResourceIcon
import com.mashup.twotoo.presenter.designsystem.icon.TwoTooIcon

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val iconTextId: Int,
    val titleTextId: Int,
) {
    Garden(
        selectedIcon = DrawableResourceIcon(TwoTooIcon.GardenSelected),
        unselectedIcon = DrawableResourceIcon(TwoTooIcon.GardenUnSelected),
        iconTextId = R.string.garden,
        titleTextId = R.string.garden,
    ),
    Home(
        selectedIcon = DrawableResourceIcon(TwoTooIcon.HomeSelected),
        unselectedIcon = DrawableResourceIcon(TwoTooIcon.HomeUnSelected),
        iconTextId = R.string.home,
        titleTextId = R.string.home,
    ),
    User(
        selectedIcon = DrawableResourceIcon(TwoTooIcon.UserSelected),
        unselectedIcon = DrawableResourceIcon(TwoTooIcon.UserUnSelected),
        iconTextId = R.string.user,
        titleTextId = R.string.user,
    ),
}
