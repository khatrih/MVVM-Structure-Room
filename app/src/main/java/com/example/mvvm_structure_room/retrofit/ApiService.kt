package com.example.mvvm_structure_room.retrofit

import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/comments")
    suspend fun getPostComments(): Response<List<CommentModel>>
}