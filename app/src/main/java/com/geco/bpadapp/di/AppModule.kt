package com.geco.bpadapp.di

import androidx.room.Room
import com.geco.bpadapp.App
import com.geco.bpadapp.data.api.APIService
import com.geco.bpadapp.data.api.ApiConfig
import com.geco.bpadapp.data.db.AppDatabase
import com.geco.bpadapp.data.db.DatabaseProvider
import com.geco.bpadapp.data.repositories.AsnRepository
import com.geco.bpadapp.data.repositories.InstansiRepository
import com.geco.bpadapp.data.repositories.KendaraanRepository
import com.geco.bpadapp.data.repositories.RekapDataKendaraanRepository
import com.geco.bpadapp.data.repositories.UserRepository
import com.geco.bpadapp.ui.dashboard.DashboardViewModel
import com.geco.bpadapp.ui.kendaraan.KendaraanDashboardViewModel
import com.geco.bpadapp.ui.login.LoginViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModule = module {
    // Database
    single { Room.databaseBuilder(get(), AppDatabase::class.java, App().db_name).build() }
    single { FirebaseAuth.getInstance() }
    single { DatabaseProvider.getDatabase(get()).userDao() }
    single { DatabaseProvider.getDatabase(get()).asnDao() }
    single { DatabaseProvider.getDatabase(get()).instansiDao() }
    single { DatabaseProvider.getDatabase(get()).kendaraanDao() }
    single { DatabaseProvider.getDatabase(get()).rekapDataKendaraanDao() }

    // Retrofit API
    single { ApiConfig.provideApiService() }
    single { get<Retrofit>().create(APIService::class.java) }

    // Repository
    single { UserRepository(get(), get(), get()) }
    single { AsnRepository(get(), get()) }
    single { InstansiRepository(get(), get()) }
    single { KendaraanRepository(get(), get()) }
    single { RekapDataKendaraanRepository(get()) }

    // ViewModels
    viewModel { LoginViewModel(get()) }
    viewModel { DashboardViewModel(get(), get(), get(), get()) }
    viewModel { KendaraanDashboardViewModel(get(), get()) }
}
