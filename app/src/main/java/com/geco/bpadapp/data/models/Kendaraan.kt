package com.geco.bpadapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "kendaraan")
data class Kendaraan(
    @PrimaryKey
    @SerializedName("nopol")
    val nopol: String,
    @SerializedName("merek")
    val merek: String,
    @SerializedName("tipe")
    val tipe: String,
    @SerializedName("jenis_roda")
    val jenisRoda: String,
    @SerializedName("nip")
    val nipAsn: String,
    @SerializedName("instansi_id")
    val instansiId: String
)