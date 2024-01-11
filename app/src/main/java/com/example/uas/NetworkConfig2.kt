package com.example.uas

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

class NetworkConfig2 {
    object RetrofitClient {
        private const val BASE_URL = "http://10.0.2.2:8000/api/"

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val apiService: ApiService2 by lazy {
            retrofit.create(ApiService2::class.java)
        }
    }
}

interface ApiService2{
    @GET("albums")
    fun getAlbumsByEmail(@Query("email") email: String): Call<List<FavouriteAlbumModel>>

    @POST("albums")
    fun createAlbum(@Body album: FavouriteAlbumModel): Call<FavouriteAlbumModel>

    @GET("albums/{id}")
    fun getAlbumById(@Path("id") id: String): Call<FavouriteAlbumModel>

    @PUT("albums/{id}")
    fun updateAlbum(@Path("id") id: String, @Body album: FavouriteAlbumModel): Call<FavouriteAlbumModel>

    @DELETE("albums/{id}")
    fun deleteAlbum(@Path("id") id: String): Call<Void>
}