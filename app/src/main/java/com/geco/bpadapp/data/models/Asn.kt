package com.geco.bpadapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "asn")
data class Asn(
    @PrimaryKey
    @SerializedName("nip")
    val nip: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("instansi_id")
    val instansiId: String
)