package com.mashup.twotoo.presenter.history.datail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.twotoo.presenter.history.datail.model.HistoryDetailInfoUiModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class HistoryDetailViewModel() : ContainerHost<HistoryDetailState, Nothing>, ViewModel() {
    override val container: Container<HistoryDetailState, Nothing> = container(HistoryDetailState(null))

    init {
        loadHistoryDetail()
    }

    private fun loadHistoryDetail() = intent {
        viewModelScope.launch {
            val newHistoryDetailInfo = HistoryDetailInfoUiModel.getHistoryDetailInfoUiModelToPreview()
            reduce { state.copy(historyDetailInfo = newHistoryDetailInfo) }
        }
    }
}
