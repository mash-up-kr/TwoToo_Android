package com.mashup.twotoo.presenter.home

import androidx.lifecycle.ViewModel

/**
 * @Created by 김현국 2023/06/23
 */
class HomeViewModel : ViewModel() {

    fun printHashCode() {
        println("Two Too ${this.hashCode()}")
    }
}
