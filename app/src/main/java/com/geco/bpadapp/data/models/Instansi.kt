package com.geco.bpadapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "instansi")
data class Instansi(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("nama")
    val nama: String
)