package com.mashup.twotoo.presenter.home

import androidx.lifecycle.ViewModel
import com.mashup.twotoo.presenter.home.di.HomeScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import usecase.token.GetAccessTokenUseCase
import javax.inject.Inject

/**
 * @Created by 김현국 2023/06/23
 */

@HomeScope
class HomeViewModel @Inject constructor(
    private val getAccessTokenUseCase: GetAccessTokenUseCase,
) : ViewModel() {

    private val _count = MutableStateFlow(0)
    val count = _count.asStateFlow()
    fun printHashCode() {
        println("로그 Two Too : ${this.hashCode()}")
    }

    fun updateCount() {
        _count.value++
    }
}
