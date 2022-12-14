package br.com.raqfc.movieapp.data.network.httpservice

import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @Headers("Accept: */*")
    @GET("{path}/{apiKey}")
    @JvmSuppressWildcards
    fun listItems(@Path("path") path: String, @Path("apiKey") apiKey: String): Call<IMDbReturnType>

    @Headers("Accept: */*")
    @GET("{path}/{apiKey}/{expression}")
    @JvmSuppressWildcards
    fun searchItems(@Path("path") path: String, @Path("apiKey") apiKey: String, @Path("expression") expression: String): Call<IMDbReturnType>
}