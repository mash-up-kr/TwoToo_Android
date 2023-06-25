package com.mashup.twotoo.presenter.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * @Created by 김현국 2023/06/23
 */
class HomeViewModel @Inject constructor() : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()
    fun printHashCode() {
        println("로그 Two Too : ${this.hashCode()}")
    }

    fun updateCount() {
        _count.value++
    }
}
