package com.maiconhellmann.architecture.data.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rate")
class Rate {
    @PrimaryKey(autoGenerate = true) var id: Long? = null

    @SerializedName("EUR")
    var euro: Double? = null

    @SerializedName("GBP")
    var britishPound: Double? = null

    @SerializedName("USD")
    var dollar: Double? = null

    @SerializedName("BRL")
    var brazilianReal: Double? = null
}