package com.app.batman.models.Dto

import com.app.batman.models.Film
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilmsDto(
    @SerializedName("Search")
    @Expose
    var films: ArrayList<Film>? = ArrayList(),

    @SerializedName("totalResults")
    @Expose
    var totalResults: String?,

    @SerializedName("Response")
    @Expose
    var Response: String?
)