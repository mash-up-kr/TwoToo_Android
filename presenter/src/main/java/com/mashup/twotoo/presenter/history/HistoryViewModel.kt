package com.mashup.twotoo.presenter.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.twotoo.presenter.history.model.HistoryItemUiModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class HistoryViewModel : ContainerHost<HistoryState, Nothing>, ViewModel() {
    override val container: Container<HistoryState, Nothing> = container(
        HistoryState(),
    )

    init {
        loadHistoryItem()
    }

    private fun loadHistoryItem() = intent {
        viewModelScope.launch {
            val newHistoryItems = HistoryItemUiModel.generateDummyHistoryItemsToPreView()
            reduce { state.copy(historyItem = newHistoryItems) }
        }
    }
}
