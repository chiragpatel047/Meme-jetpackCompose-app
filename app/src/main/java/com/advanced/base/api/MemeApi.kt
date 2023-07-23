package com.advanced.base.api

import com.advanced.base.models.MemeModel
import retrofit2.Response
import retrofit2.http.GET

interface MemeApi {

    @GET("/gimme")
    suspend fun getMeme() : Response<MemeModel>

}