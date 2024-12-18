package com.geco.bpadapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.geco.bpadapp.data.models.Kendaraan

@Dao
interface KendaraanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKendaraan(kendaraan: Kendaraan)

    @Insert
    suspend fun insertAllKendaraan(kendaraanList: List<Kendaraan>)

    @Query("SELECT * FROM kendaraan WHERE nopol = :nopol")
    suspend fun getKendaraanByNopol(nopol: String): Kendaraan?

    @Query("SELECT * FROM kendaraan")
    suspend fun getAllKendaraan(): List<Kendaraan>

    @Query("SELECT * FROM kendaraan WHERE nipAsn = :nipAsn")
    suspend fun getKendaraanByNipAsn(nipAsn: String): List<Kendaraan>?

    @Query("SELECT * FROM kendaraan WHERE instansiId = :instansiId")
    suspend fun getKendaraanByInstansi(instansiId: String): List<Kendaraan>?

    @Update
    suspend fun updateKendaraan(kendaraan: Kendaraan)

    @Query("DELETE FROM kendaraan WHERE nopol = :nopol")
    suspend fun deleteKendaraanByNopol(nopol: String)
}