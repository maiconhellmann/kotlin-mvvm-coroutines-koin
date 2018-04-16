package com.maiconhellmann.architecture.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.maiconhellmann.architecture.data.model.Movie

class SearchMovieDto : Dto() {
    @SerializedName("Search")
    var search: List<Movie>? = null
}