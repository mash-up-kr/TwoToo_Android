package com.mashup.twotoo.presenter.util

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

fun String.toIncodeUrl(): String {
    return URLEncoder.encode(this, StandardCharsets.UTF_8.toString())
}
