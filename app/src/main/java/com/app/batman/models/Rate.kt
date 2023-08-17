package com.app.batman.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Rate(
    @SerializedName("Source")
    @Expose
    val source: String?,

    @SerializedName("Value")
    @Expose
    val value: String?
)