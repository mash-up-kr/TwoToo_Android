package com.mashup.twotoo.presenter.di

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * @Created by 김현국 2023/06/23
 */

@Composable
inline fun <reified T : ViewModel> daggerViewModel(
    key: String? = null,
    crossinline viewModelInstanceCreator: () -> T,
    factory: ViewModelProvider.Factory,
): T =
    viewModel(
        modelClass = T::class.java,
        key = key,
        factory = factory,
    )
