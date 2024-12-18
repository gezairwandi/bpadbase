package com.geco.bpadapp.data.repositories

import com.geco.bpadapp.data.api.APIService
import com.geco.bpadapp.data.db.InstansiDao
import com.geco.bpadapp.data.models.Instansi

class InstansiRepository(private val instansiDao: InstansiDao, private val apiService: APIService) {

    suspend fun fetchInstansiFromFirebase(authToken: String): List<Instansi> {
        val response = apiService.getInstansi(authToken)
        return if (response.isSuccessful) {
            response.body()?.values?.toList() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun saveAsnToLocal(instansi: List<Instansi>) {
        instansiDao.insertAllInstansi(instansi)
    }

    suspend fun getInstansiById(id: String): Instansi? {
        return instansiDao.getInstansiById(id)
    }

    suspend fun getInstansiByName(name: String): Instansi? {
        return instansiDao.getInstansiByName(name)
    }

    suspend fun getAllInstansiFromLocal(): List<Instansi> {
        return instansiDao.getAllInstansi()
    }
}
