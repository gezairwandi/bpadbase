package com.geco.bpadapp.data.repositories

import com.geco.bpadapp.data.api.APIService
import com.geco.bpadapp.data.db.AsnDao
import com.geco.bpadapp.data.db.KendaraanDao
import com.geco.bpadapp.data.models.Asn
import com.geco.bpadapp.data.models.Kendaraan

class KendaraanRepository(private val kendaraanDao: KendaraanDao, private val apiService: APIService) {

    suspend fun fetchKendaraanFromFirebase(authToken: String): List<Kendaraan> {
        val response = apiService.getKendaraan(authToken)
        return if (response.isSuccessful) {
            response.body()?.values?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun saveAsnToLocal(kendaraan: List<Kendaraan>) {
        kendaraanDao.insertAllKendaraan(kendaraan)
    }

    suspend fun getKendaraanByNopol(nopol: String): Kendaraan? {
        return kendaraanDao.getKendaraanByNopol(nopol)
    }

    suspend fun getKendaraanByNipAsn(nipAsn: String): List<Kendaraan>? {
        return kendaraanDao.getKendaraanByNipAsn(nipAsn)
    }

    suspend fun getKendaraanByInstansiId(instansiId: String): List<Kendaraan>? {
        return kendaraanDao.getKendaraanByNipAsn(instansiId)
    }

    suspend fun getAllKendaraanFromLocal(): List<Kendaraan> {
        return kendaraanDao.getAllKendaraan()
    }
}
