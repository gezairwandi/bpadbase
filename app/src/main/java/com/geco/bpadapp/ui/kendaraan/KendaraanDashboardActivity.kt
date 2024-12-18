package com.geco.bpadapp.ui.kendaraan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.geco.bpadapp.data.models.Kendaraan
import com.geco.bpadapp.databinding.ActivityKendaraanDashboardBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class KendaraanDashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKendaraanDashboardBinding
    private lateinit var kendaraanAdapter: KendaraanAdapter
    private val kendaraanDashboardViewModel: KendaraanDashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKendaraanDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val token = intent.getStringExtra("auth_token")
        val instansiId = intent.getStringExtra("instansi_id")
        token?.let {
            kendaraanDashboardViewModel.fetchAsnFromFirebase(it)
            kendaraanDashboardViewModel.fetchKendaraanFromFirebase(it)

            binding.swipeRefreshLayout.setOnRefreshListener {
                kendaraanDashboardViewModel.fetchAsnFromFirebase(it)
                kendaraanDashboardViewModel.fetchKendaraanFromFirebase(it)
                instansiId?.let { instansiId->
                    loadKendaraanList(instansiId)
                }
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

        kendaraanDashboardViewModel.kendaraanList.observe(this){ assets ->
            setupRecyclerview(assets)
        }

//        setupSpinner()
    }

    private fun loadKendaraanList(instansiId: String){
        kendaraanDashboardViewModel.fetchKendaraanByInstansiId(instansiId)
    }

    private fun setupRecyclerview(kendaraanList: List<Kendaraan>){
        kendaraanAdapter = KendaraanAdapter(kendaraanList)
        binding.rvKendaraan.apply {
            layoutManager = LinearLayoutManager(this@KendaraanDashboardActivity)
            adapter = kendaraanAdapter
        }
    }

//    private fun setupSpinner(){
//        val assetTypes = listOf("All", "Type", "Owner", "Name")
//        val spinnerAdapter = ArrayAdapter(this, R.layout.simple_spinner_item, assetTypes)
//        spinnerAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        binding.spinnerAssetType.adapter = spinnerAdapter
//        var selectedType = ""
//        // Filter by Asset Type (Spinner)
//        binding.spinnerAssetType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                selectedType = parentView?.getItemAtPosition(position) as String
//                when(selectedType){
//                    "Type" -> selectedType = "type"
//                    "Owner" -> selectedType = "ownerId"
//                    "Name" -> selectedType = "name"
//                    else-> selectedType = "all"
//                }
//            }
//
//            override fun onNothingSelected(parentView: AdapterView<*>?) {}
//        }
//
//        binding.btnFilter.setOnClickListener {
//            if (selectedType == "all") {
//                binding.etFilterByName.text.clear()
//                dashboardViewModel.loadAssetsFromLocal()
//            } else {
//                dashboardViewModel.filterAssetsBy(
//                    selectedType,
//                    binding.etFilterByName.text.toString()
//                )
//            }
//        }
//
//        dashboardViewModel.filteredAssets.observe(this){ assets ->
//            setupRecyclerview(assets)
//        }
//    }
}