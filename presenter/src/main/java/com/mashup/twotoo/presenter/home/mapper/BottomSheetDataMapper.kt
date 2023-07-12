package com.mashup.twotoo.presenter.home.mapper

import com.mashup.twotoo.presenter.designsystem.component.bottomsheet.BottomSheetData
import model.commit.request.CommitRequestDomainModel

fun BottomSheetData.AuthenticateData.toDomainModel(): CommitRequestDomainModel {
    return CommitRequestDomainModel(
        text = this.text,
        img = this.image.toString(),
    )
}
