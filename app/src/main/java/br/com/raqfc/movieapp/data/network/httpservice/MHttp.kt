package br.com.raqfc.movieapp.data.network.httpservice

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import ru.gildor.coroutines.okhttp.await

//object MHttp {
//    suspend fun post(url: String, body: HashMap<String, String>): Result<String> {
//        return withContext(Dispatchers.IO) {
//            try {
//                val requestBody = RequestBody.create(
//                    MediaType.parse("application/json; charset=utf-8"),
//                    Gson().toJson(body)
//                )
//
//                val request = okhttp3.Request.Builder()
//                    .url(url)
//                    .post(requestBody)
//                    .build()
//
//                val response = OkHttpClient().newCall(request).await()
//
//                if (response.isSuccessful && response.body() != null) {
//                    kotlin.runCatching {
//                        val stringBody = response.body()!!.string()
//                        stringBody
//                    }
//                } else {
//                    Result.failure(Exception())
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                Log.e("MHttp", e.message ?: "")
//                Result.failure(e)
//            }
//        }
//    }
//
//}
