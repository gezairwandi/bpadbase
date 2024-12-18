package com.geco.bpadapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.geco.bpadapp.data.models.Asn
import com.geco.bpadapp.data.models.Instansi
import com.geco.bpadapp.data.models.Kendaraan
import com.geco.bpadapp.data.models.RekapDataKendaraan
import com.geco.bpadapp.data.models.User

@Database(
    entities = [User::class, Asn::class, Instansi::class, Kendaraan::class, RekapDataKendaraan::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun asnDao(): AsnDao
    abstract fun instansiDao(): InstansiDao
    abstract fun kendaraanDao(): KendaraanDao
    abstract fun rekapDataKendaraanDao(): RekapDataKendaraanDao
}