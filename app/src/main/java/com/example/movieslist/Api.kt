package com.example.movieslist

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = "7efdcf98fe3a83159c29307ef7784db1",
        @Query("page") page: Int = 1
    ): Response<GetMoviesResponse>

//    @GET("movie/top_rated")
//    suspend fun getTopRatedMovies(
//        @Query("api_key") apiKey: String = "7efdcf98fe3a83159c29307ef7784db1",
//        @Query("page") page: Int
//    ): Response<GetMoviesResponse>
//
//    @GET("movie/upcoming")
//    suspend fun getUpcomingMovies(
//        @Query("api_key") apiKey: String = "7efdcf98fe3a83159c29307ef7784db1",
//        @Query("page") page: Int
//    ): Response<GetMoviesResponse>
}