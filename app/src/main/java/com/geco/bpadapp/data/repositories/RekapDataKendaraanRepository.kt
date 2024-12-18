package com.geco.bpadapp.data.repositories

import com.geco.bpadapp.data.db.RekapDataKendaraanDao
import com.geco.bpadapp.data.models.RekapDataKendaraan

class RekapDataKendaraanRepository(private val rekapDataKendaraanDao: RekapDataKendaraanDao) {

    suspend fun saveAllRekapDataKendaraanToLocal(rekapDataKendaraan: List<RekapDataKendaraan>) {
        rekapDataKendaraanDao.insertAllRekapDataKendaraan(rekapDataKendaraan)
    }

    suspend fun saveRekapDataKendaraanToLocal(rekapDataKendaraan: RekapDataKendaraan) {
        rekapDataKendaraanDao.insertData(rekapDataKendaraan)
    }

    suspend fun getRekapDataKendaraanById(id: String): RekapDataKendaraan? {
        return rekapDataKendaraanDao.getRekapDataKendaraanById(id)
    }

    suspend fun getRekapDataKendaraanByInstansi(instansi: String): List<RekapDataKendaraan>? {
        return rekapDataKendaraanDao.getRekapDataKendaraanByInstansi(instansi)
    }

    suspend fun getAllRekapDataKendaraanFromLocal(): List<RekapDataKendaraan> {
        return rekapDataKendaraanDao.getAllRekapDataKendaraan()
    }
}
