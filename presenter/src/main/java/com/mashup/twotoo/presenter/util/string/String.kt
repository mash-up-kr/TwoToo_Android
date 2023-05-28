package com.mashup.twotoo.presenter.util.string

import android.content.Context
import androidx.annotation.StringRes

fun getString(context: Context, @StringRes id: Int): String {
    return context.getString(id)
}
