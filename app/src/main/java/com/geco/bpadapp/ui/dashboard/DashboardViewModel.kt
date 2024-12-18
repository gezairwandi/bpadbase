package com.geco.bpadapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geco.bpadapp.data.models.Asn
import com.geco.bpadapp.data.models.Asset
import com.geco.bpadapp.data.models.Instansi
import com.geco.bpadapp.data.models.Kendaraan
import com.geco.bpadapp.data.models.RekapDataKendaraan
import com.geco.bpadapp.data.repositories.AsnRepository
import com.geco.bpadapp.data.repositories.InstansiRepository
import com.geco.bpadapp.data.repositories.KendaraanRepository
import com.geco.bpadapp.data.repositories.RekapDataKendaraanRepository
import kotlinx.coroutines.launch

class DashboardViewModel(private val asnRepository: AsnRepository,
                         private val instansiRepository: InstansiRepository,
                         private val kendaraanRepository: KendaraanRepository,
                         private val rekapDataKendaraanRepository: RekapDataKendaraanRepository
) : ViewModel() {

    private val _asnList = MutableLiveData<List<Asn>>()
    val asnList: LiveData<List<Asn>> get() = _asnList

    private val _instansiList = MutableLiveData<List<Instansi>>()
    val instansiList: LiveData<List<Instansi>> get() = _instansiList

    private val _instansi = MutableLiveData<Instansi>()
    val instansi: LiveData<Instansi> get() = _instansi

    private val _kendaraanList = MutableLiveData<List<Kendaraan>>()
    val kendaraanList: LiveData<List<Kendaraan>> get() = _kendaraanList

    private val _rekapDataKendaraan = MutableLiveData<List<RekapDataKendaraan>>()
    val rekapDataKendaraan: LiveData<List<RekapDataKendaraan>> get() = _rekapDataKendaraan


    private val _filteredAssets = MutableLiveData<List<Asset>>()
    val filteredAssets: LiveData<List<Asset>> get() = _filteredAssets

    fun fetchAsnFromFirebase(authToken: String) {
        viewModelScope.launch {
            val asnFromFirebase = asnRepository.fetchAsnFromFirebase(authToken)
            asnRepository.saveAsnToLocal(asnFromFirebase)
            _asnList.value = asnFromFirebase
        }
    }

    fun fetchInstansiFromFirebase(authToken: String) {
        viewModelScope.launch {
            val instansiFromFirebase = instansiRepository.fetchInstansiFromFirebase(authToken)
            instansiRepository.saveAsnToLocal(instansiFromFirebase)
            _instansiList.value = instansiFromFirebase
        }
    }

    fun fetchKendaraanFromFirebase(authToken: String) {
        viewModelScope.launch {
            val kendaraanFromFirebase = kendaraanRepository.fetchKendaraanFromFirebase(authToken)
            kendaraanRepository.saveAsnToLocal(kendaraanFromFirebase)
            _kendaraanList.value = kendaraanFromFirebase
        }
    }

    fun filterInstansiByInstansiName(instansiName: String) {
        viewModelScope.launch {
            _instansi.value = instansiRepository.getInstansiByName(instansiName)
        }
    }

    fun filterAssetsBy(whereKey: String, whereValue: String) {
        viewModelScope.launch {
//            _filteredAssets.value =
//                repository.getAssetsBy(whereKey = whereKey, whereValue = whereValue)
        }
    }

    fun loadAssetsFromLocal() {
        viewModelScope.launch {
//            _assets.value = repository.getAllAssetsFromLocal()
        }
    }

    fun insertRekapData(rekapDataKendaraan: RekapDataKendaraan){
        viewModelScope.launch {
            rekapDataKendaraanRepository.saveRekapDataKendaraanToLocal(rekapDataKendaraan)
        }
    }

    fun loadRekapDataKendaraanFromLocal() {
        viewModelScope.launch {
            _rekapDataKendaraan.value = rekapDataKendaraanRepository.getAllRekapDataKendaraanFromLocal()
        }
    }
}

