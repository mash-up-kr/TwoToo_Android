package com.mashup.twotoo.presenter.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * @Created by 김현국 2023/06/23
 */

@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    viewModelStoreOwner: ViewModelStoreOwner,
    crossinline viewModelInstanceCreator: () -> T,
): T =
    viewModel(
        viewModelStoreOwner = viewModelStoreOwner,
        modelClass = T::class.java,
        key = T::class.simpleName,
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return viewModelInstanceCreator() as T
            }
        },
    )
