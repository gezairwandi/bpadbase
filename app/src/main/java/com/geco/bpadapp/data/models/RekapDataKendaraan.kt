package com.geco.bpadapp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "rekap_data_kendaraan")
data class RekapDataKendaraan(
    @PrimaryKey
    @SerializedName("id")
    val id: String,
    @SerializedName("instansi")
    val instansi: String,
    @SerializedName("jenis_roda")
    val jenis_roda: String,
    @SerializedName("jumlah")
    val jumlah: String,
    @SerializedName("jumlah_asn_penanggung_jawab")
    val jumlahAsn: String
)