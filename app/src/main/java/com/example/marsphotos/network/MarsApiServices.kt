package com.example.marsphotos.network

import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.http.GET

private const val BASE_URL ="https://android-kotlin-fun-mars-server.appspot.com"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
    .baseUrl(BASE_URL)
    .build()
// Scalars convertors has the job to convert the data from json to string and other formats
 interface  MarsApiServices{

    // this appends the photos at the end of the base url, when the get Photos function is called
    @GET("photos")
    suspend fun getPhotos():List<MarsPhoto>
}
object MarsApi{
    // Creates a singleton object
    val retrofitService: MarsApiServices by lazy {
        retrofit.create(MarsApiServices::class.java)
    }
}
//class MarsApiServices {
//
//}