package com.example.uas

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class NetworkConfig {

    var BASE_URL = "https://spotify23.p.rapidapi.com/"

    fun getApiService(): ApiService {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()

                // Add headers here
                val request = original.newBuilder()
                    .header("X-RapidAPI-Key", "67536ce029msh6feaaf8e70f45c5p1cbeb1jsndbfc93954c73")
                    .header("X-RapidAPI-Host", "spotify23.p.rapidapi.com")
                    .method(original.method, original.body)
                    .build()

                chain.proceed(request)
            }
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiService::class.java)
    }

}

interface ApiService {
    @GET("search/")
    fun searchArtists(
        @Query("q") query: String,
        @Query("type") type: String,
        @Query("offset") offset : Int = 0,
        @Query("limit") limit : Int = 20,
        @Query("numberOfTopResult") numberOfTopResult : Int = 5
    ): Call<SearchModel>
}


