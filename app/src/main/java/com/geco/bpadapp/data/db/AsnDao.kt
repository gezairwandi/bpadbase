package com.geco.bpadapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.geco.bpadapp.data.models.Asn

@Dao
interface AsnDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsn(asn: Asn)

    @Insert
    suspend fun insertAllAsn(asnList: List<Asn>)

    @Query("SELECT * FROM asn WHERE nip = :nip")
    suspend fun getAsnById(nip: String): Asn?

    @Query("SELECT * FROM asn")
    suspend fun getAllAsn(): List<Asn>

    @Query("SELECT * FROM asn WHERE instansiId = :instansiId")
    suspend fun getUserByInstansiId(instansiId: String): Asn?

    @Update
    suspend fun updateAsn(asn: Asn)

    @Query("DELETE FROM asn WHERE nip = :nip")
    suspend fun deleteAsnByNip(nip: String)
}