package com.maiconhellmann.architecture.data.remote.dto

import com.google.gson.annotations.SerializedName

open class Dto(
        var totalResults: Long? = null,

        @SerializedName("Response")
        var response: Boolean? = null,

        @SerializedName("Error")
        var error: String? = null
)