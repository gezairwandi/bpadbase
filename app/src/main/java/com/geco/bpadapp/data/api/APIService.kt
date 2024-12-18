package com.geco.bpadapp.data.api

import com.geco.bpadapp.data.models.Asn
import com.geco.bpadapp.data.models.Instansi
import com.geco.bpadapp.data.models.Kendaraan
import com.geco.bpadapp.data.models.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("users.json")
    suspend fun getUsers(
        @Query("auth") token: String
    ): Response<Map<String, User>>

    @GET("asn.json")
    suspend fun getAsn(
        @Query("auth") token: String
    ): Response<Map<String, Asn>>

    @GET("instansi.json")
    suspend fun getInstansi(
        @Query("auth") token: String
    ): Response<Map<String, Instansi>>

    @GET("kendaraan.json")
    suspend fun getKendaraan(
        @Query("auth") token: String
    ): Response<Map<String, Kendaraan>>
}