package com.mashup.twotoo.presenter.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * @Created by 김현국 2023/06/23
 */

@Suppress("UNCHECKED_CAST")
@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    crossinline viewModelInstanceCreator: () -> T,
): T =
    viewModel(
        modelClass = T::class.java,
        key = T::class.simpleName,
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInstanceCreator() as T
            }
        },
    )
