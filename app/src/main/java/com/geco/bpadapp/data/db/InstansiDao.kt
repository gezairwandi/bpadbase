package com.geco.bpadapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.geco.bpadapp.data.models.Instansi

@Dao
interface InstansiDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInstansi(instansi: Instansi)

    @Insert
    suspend fun insertAllInstansi(instansi: List<Instansi>)

    @Query("SELECT * FROM instansi WHERE id = :id")
    suspend fun getInstansiById(id: String): Instansi?

    @Query("SELECT * FROM instansi WHERE nama = :name")
    suspend fun getInstansiByName(name: String): Instansi?

    @Query("SELECT * FROM instansi")
    suspend fun getAllInstansi(): List<Instansi>

    @Update
    suspend fun updateInstansi(instansi: Instansi)

    @Query("DELETE FROM instansi WHERE id = :instansiId")
    suspend fun deleteInstansiById(instansiId: String)
}