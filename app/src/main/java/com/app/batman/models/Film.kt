package com.app.batman.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Film")
data class Film(
    @ColumnInfo(name = "title")
    @SerializedName("Title")
    @Expose
    val title: String? = "",

    @ColumnInfo(name = "year")
    @SerializedName("Year")
    @Expose
    val year: String? = "",

    @ColumnInfo(name = "rated")
    @SerializedName("Rated")
    @Expose
    val rated: String? = "",

    @ColumnInfo(name = "released")
    @SerializedName("Released")
    @Expose
    val released: String? = "",

    @ColumnInfo(name = "runtime")
    @SerializedName("Runtime")
    @Expose
    val runtime: String? = "",

    @ColumnInfo(name = "genre")
    @SerializedName("Genre")
    @Expose
    val genre: String? = "",

    @ColumnInfo(name = "director")
    @SerializedName("Director")
    @Expose
    val director: String? = "",

    @ColumnInfo(name = "writer")
    @SerializedName("Writer")
    @Expose
    val writer: String? = "",

    @ColumnInfo(name = "actors")
    @SerializedName("Actors")
    @Expose
    val actors: String? = "",

    @ColumnInfo(name = "plot")
    @SerializedName("Plot")
    @Expose
    val plot: String? = "",

    @ColumnInfo(name = "language")
    @SerializedName("Language")
    @Expose
    val language: String? = "",

    @ColumnInfo(name = "country")
    @SerializedName("Country")
    @Expose
    val country: String? = "",

    @ColumnInfo(name = "awards")
    @SerializedName("Awards")
    @Expose
    val awards: String? = "",

    @ColumnInfo(name = "poster")
    @SerializedName("Poster")
    @Expose
    val poster: String? = "",

    @ColumnInfo(name = "ratings")
    @SerializedName("Ratings")
    @Expose
    val ratings: ArrayList<Rate>? = ArrayList(),

    @ColumnInfo(name = "metaScore")
    @SerializedName("Metascore")
    @Expose
    val metaScore: String? = "",

    @ColumnInfo(name = "imdbRating")
    @SerializedName("imdbRating")
    @Expose
    val imdbRating: String? = "",

    @ColumnInfo(name = "imdbVotes")
    @SerializedName("imdbVotes")
    @Expose
    val imdbVotes: String? = "",

    @PrimaryKey
    @ColumnInfo(name = "imdbID")
    @SerializedName("imdbID")
    @Expose
    val imdbId: String = "",

    @ColumnInfo(name = "type")
    @SerializedName("Type")
    @Expose
    val type: String? = "",

    @ColumnInfo(name = "dvd")
    @SerializedName("DVD")
    @Expose
    val dvd: String? = "",

    @ColumnInfo(name = "boxOffice")
    @SerializedName("BoxOffice")
    @Expose
    val boxOffice: String? = "",

    @ColumnInfo(name = "production")
    @SerializedName("Production")
    @Expose
    val production: String? = "",

    @ColumnInfo(name = "Website")
    @SerializedName("Website")
    @Expose
    val website: String? = "",

    @ColumnInfo(name = "totalSeason")
    @SerializedName("totalSeasons")
    @Expose
    val totalSeason: String? = "",

    @ColumnInfo(name = "response")
    @SerializedName("Response")
    @Expose
    val response: String? = ""
)
