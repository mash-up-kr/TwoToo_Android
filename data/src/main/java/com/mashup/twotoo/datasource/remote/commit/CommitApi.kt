package com.mashup.twotoo.datasource.remote.commit

import com.mashup.twotoo.datasource.remote.commit.request.CheerRequest
import com.mashup.twotoo.datasource.remote.commit.response.Commit
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import util.NetworkResult

interface CommitApi {
    @Multipart
    @POST("/commit")
    suspend fun commit(
        @Part text: MultipartBody.Part,
        @Part challengeNo: MultipartBody.Part,
        @Part img: MultipartBody.Part,
    ): NetworkResult<Commit>

    @GET("/commit{commitNo}")
    suspend fun getCommitByNo(@Path("commitNo") commitNo: Int): NetworkResult<Commit>

    @POST("/commit/{commitNo}/comment")
    suspend fun cheerByNo(
        @Path("commitNo") commitNo: Int,
        @Body cheerRequest: CheerRequest,
    ): NetworkResult<Commit>
}
