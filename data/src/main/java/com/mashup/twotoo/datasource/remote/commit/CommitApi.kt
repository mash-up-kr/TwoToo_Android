package com.mashup.twotoo.datasource.remote.commit

import com.mashup.twotoo.datasource.remote.commit.request.CommitRequest
import com.mashup.twotoo.datasource.remote.commit.response.Commit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * @Created by 김현국 2023/07/10
 */
interface CommitApi {
    @POST("/commit")
    suspend fun commit(
        @Body commitRequest: CommitRequest,
    ): Commit

    @GET("/commit{commitNo}")
    suspend fun getCommitByNo(@Path("commitNo") commitNo: String): Commit

    @POST("/commit/{commitNo}/comment")
    suspend fun cheerByNo(@Path("commitNo") commitNo: String): Commit
}
