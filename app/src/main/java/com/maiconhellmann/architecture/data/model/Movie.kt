package com.maiconhellmann.architecture.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movie")
class Movie {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @SerializedName("Title")
    var title: String? = null

    @SerializedName("Year")
    var year: String? = null

    @SerializedName("Rated")
    var rated: String? = null

    @SerializedName("Runtime")
    var runTime: String? = null

    @SerializedName("Genre")
    var genre: String? = null

    @SerializedName("Director")
    var director: String? = null

    @SerializedName("Writer")
    var writer: String? = null

    @SerializedName("Plot")
    var plot: String? = null

    @SerializedName("Awards")
    var awards: String? = null

    @SerializedName("Poster")
    var poster: String? = null

//    var ratings: List<Rating>

}