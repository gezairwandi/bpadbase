package com.geco.bpadapp.data.repositories

import com.geco.bpadapp.data.api.APIService
import com.geco.bpadapp.data.db.AsnDao
import com.geco.bpadapp.data.models.Asn

class AsnRepository(private val asnDao: AsnDao, private val apiService: APIService) {

    suspend fun fetchAsnFromFirebase(authToken: String): List<Asn> {
        val response = apiService.getAsn(authToken)
        return if (response.isSuccessful) {
            response.body()?.values?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun saveAsnToLocal(assets: List<Asn>) {
        asnDao.insertAllAsn(assets)
    }

    suspend fun getAsnById(nip: String): Asn? {
        return asnDao.getAsnById(nip)
    }

    suspend fun getAsnByInstansiId(instansiId: String): Asn? {
        return asnDao.getUserByInstansiId(instansiId)
    }

    suspend fun getAllAsnFromLocal(): List<Asn> {
        return asnDao.getAllAsn()
    }
}
