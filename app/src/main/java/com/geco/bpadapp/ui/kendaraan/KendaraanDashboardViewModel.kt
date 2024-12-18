package com.geco.bpadapp.ui.kendaraan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.geco.bpadapp.data.models.Asn
import com.geco.bpadapp.data.models.Kendaraan
import com.geco.bpadapp.data.repositories.AsnRepository
import com.geco.bpadapp.data.repositories.KendaraanRepository
import kotlinx.coroutines.launch

class KendaraanDashboardViewModel(
    private val asnRepository: AsnRepository, private val kendaraanRepository: KendaraanRepository
) : ViewModel() {

    private val _asnList = MutableLiveData<List<Asn>>()
    val asnList: LiveData<List<Asn>> get() = _asnList

    private val _kendaraanList = MutableLiveData<List<Kendaraan>>()
    val kendaraanList: LiveData<List<Kendaraan>> get() = _kendaraanList

    fun fetchAsnFromFirebase(authToken: String) {
        viewModelScope.launch {
            val asnFromFirebase = asnRepository.fetchAsnFromFirebase(authToken)
            asnRepository.saveAsnToLocal(asnFromFirebase)
            _asnList.value = asnFromFirebase
        }
    }

    fun fetchKendaraanFromFirebase(authToken: String) {
        viewModelScope.launch {
            val kendaraanFromFirebase = kendaraanRepository.fetchKendaraanFromFirebase(authToken)
            kendaraanRepository.saveAsnToLocal(kendaraanFromFirebase)
            _kendaraanList.value = kendaraanFromFirebase
        }
    }

    fun fetchKendaraanByInstansiId(instansiId: String) {
        viewModelScope.launch {
            _kendaraanList.value = kendaraanRepository.getKendaraanByInstansiId(instansiId)
        }
    }

    fun filterAssetsByOwner(ownerId: String) {
        viewModelScope.launch {
//            _filteredAssets.value = repository.getAssetsByOwnerId(ownerId)
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
}

