package br.com.raqfc.movieapp.data.network.httpservice

import br.com.raqfc.movieapp.data.dto.FullContentDTO
import retrofit2.Call
import retrofit2.http.*

interface RetrofitService {
    @Headers("Accept: */*")
    @GET("{path}/{apiKey}")
    @JvmSuppressWildcards
    fun listItems(@Path("path") path: String, @Path("apiKey") apiKey: String): Call<IMDbFetchReturnType>

    @Headers("Accept: */*")
    @GET("{path}/{apiKey}/{expression}")
    @JvmSuppressWildcards
    fun searchItems(@Path("path") path: String, @Path("apiKey") apiKey: String, @Path("expression") expression: String): Call<IMDbSearchReturnType>


    @Headers("Accept: */*")
    @GET("{path}/{apiKey}/{id}/{params}")
    @JvmSuppressWildcards
    fun fetchItem(@Path("path") path: String, @Path("apiKey") apiKey: String, @Path("id") id: String, @Path("params") params: String): Call<FullContentDTO>

}