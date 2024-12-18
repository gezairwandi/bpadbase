package com.geco.bpadapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.geco.bpadapp.data.models.RekapDataKendaraan

@Dao
interface RekapDataKendaraanDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(rekapDataKendaraan: RekapDataKendaraan)

    @Insert
    suspend fun insertAllRekapDataKendaraan(rekapDataKendaraan: List<RekapDataKendaraan>)

    @Query("SELECT * FROM rekap_data_kendaraan WHERE id = :id")
    suspend fun getRekapDataKendaraanById(id: String): RekapDataKendaraan?

    @Query("SELECT * FROM rekap_data_kendaraan")
    suspend fun getAllRekapDataKendaraan(): List<RekapDataKendaraan>

    @Query("SELECT * FROM rekap_data_kendaraan WHERE instansi = :instansi")
    suspend fun getRekapDataKendaraanByInstansi(instansi: String): List<RekapDataKendaraan>?

    @Update
    suspend fun updateRekapDataKendaraan(rekapdataKendaraan: RekapDataKendaraan)

    @Query("DELETE FROM rekap_data_kendaraan WHERE id = :id")
    suspend fun deleteRekapDataKendaraanById(id: String)
}