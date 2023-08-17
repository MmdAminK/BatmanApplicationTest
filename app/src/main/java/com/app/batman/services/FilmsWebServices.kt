package com.app.batman.services

import com.app.batman.models.Dto.FilmsDto
import com.app.batman.models.Film
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmsWebServices {

    @GET(".")
    suspend fun getFilms(
        @Query("apikey") apiKey: String,
        @Query("s") s: String
    ): Response<FilmsDto?>

    @GET(".")
    suspend fun getFilmDetail(
        @Query("apikey") apiKey: String,
        @Query("i") imdbId: String
    ): Response<Film?>
}