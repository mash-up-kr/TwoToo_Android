package com.mashup.twotoo.presenter.util

fun String.extractScreenName(): String? {
    val pattern = Regex("""(.+\/screen)""")
    val matchResult = pattern.find(this)

    return matchResult?.groupValues?.get(1)
}
