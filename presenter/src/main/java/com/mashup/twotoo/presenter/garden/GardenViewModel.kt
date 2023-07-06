package com.mashup.twotoo.presenter.garden

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mashup.twotoo.presenter.garden.model.ChallengeCardInfoUiModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GardenViewModel : ContainerHost<GardenState, Nothing>, ViewModel() {
    override val container: Container<GardenState, Nothing> = container(GardenState())

    init {
        loadChallengeCardInfos()
    }

    private fun loadChallengeCardInfos() = intent {
        viewModelScope.launch {
            val newChallengeCardInfos = ChallengeCardInfoUiModel.getChallengeCardInfoToPreview()
            reduce { state.copy(challengeCardInfos = newChallengeCardInfos) }
        }
    }
}
