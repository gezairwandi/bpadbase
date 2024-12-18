package com.geco.bpadapp.ui.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.geco.bpadapp.data.models.RekapDataKendaraan
import com.geco.bpadapp.databinding.ActivityDashboardBinding
import com.geco.bpadapp.ui.kendaraan.KendaraanDashboardActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var assetAdapter: RekapDataAdapter
    private val dashboardViewModel: DashboardViewModel by viewModel()
    private lateinit var token: String
    private lateinit var instansiId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        token = intent.getStringExtra("auth_token")?:""
        token.let {
            dashboardViewModel.fetchAsnFromFirebase(it)
            dashboardViewModel.fetchInstansiFromFirebase(it)
            dashboardViewModel.fetchKendaraanFromFirebase(it)

            // Set up Pull-to-Refresh
            binding.swipeRefreshLayout.setOnRefreshListener {
                dashboardViewModel.fetchAsnFromFirebase(it)
                dashboardViewModel.fetchInstansiFromFirebase(it)
                dashboardViewModel.fetchKendaraanFromFirebase(it)
                binding.swipeRefreshLayout.isRefreshing = false
            }
            setupDummyData()

//            setupRerkapDataKendaraan()
        }

        dashboardViewModel.rekapDataKendaraan.observe(this) {
            setupRecyclerview(it)
        }

//        dashboardViewModel.assets.observe(this){ assets ->
//            setupRecyclerview(assets)
//        }

//        setupSpinner()
    }

    private fun setupRecyclerview(rekapDataKendaraan: List<RekapDataKendaraan>) {
        assetAdapter = RekapDataAdapter(rekapDataKendaraan){ data->
            dashboardViewModel.filterInstansiByInstansiName(data.instansi)
            dashboardViewModel.instansi.observe(this){ instansi->
                instansiId = instansi.id
                toKendaraanDashboardByInstansi(token, instansiId)
            }

        }
        binding.rvAssets.apply {
            layoutManager = LinearLayoutManager(this@DashboardActivity)
            adapter = assetAdapter
        }

    }

    private fun setupDummyData(){
        dashboardViewModel.insertRekapData(RekapDataKendaraan(
            id = "1",
            instansi = "Dinas Pendidikan",
            jenis_roda = "Roda 2",
            jumlah = "4",
            jumlahAsn = "4"))

        dashboardViewModel.insertRekapData(RekapDataKendaraan(
            id = "2",
            instansi = "Dinas Pendidikan",
            jenis_roda = "Roda 3",
            jumlah = "5",
            jumlahAsn = "3"))

        dashboardViewModel.insertRekapData(RekapDataKendaraan(
            id = "3",
            instansi = "Dinas Lingkungan Hidup",
            jenis_roda = "Roda 4",
            jumlah = "3",
            jumlahAsn = "1"))
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

    private fun toKendaraanDashboardByInstansi(token: String, instansiId: String) {
        val intent = Intent(this, KendaraanDashboardActivity::class.java)
        intent.putExtra("auth_token", token)
        intent.putExtra("instansi_id", instansiId)
        startActivity(intent)
    }

    private fun setupRerkapDataKendaraan(){
        dashboardViewModel.filterKendaraanByTipe("Roda 2")
        dashboardViewModel.filterKendaraanByTipe("Roda 3")
        dashboardViewModel.filterKendaraanByTipe("Roda 4")

        dashboardViewModel.roda2.observe(this){
            var jumlah = 0
            var jumlahAsn = 0
            it.forEach {
                jumlah++
                if(it.nipAsn.toInt()!=0){
                    jumlahAsn++
                }
            }

            dashboardViewModel.insertRekapData(RekapDataKendaraan(
                id = "1",
                instansi = it.get(0).instansiId,
                jenis_roda = "Roda 2",
                jumlah = jumlah.toString(),
                jumlahAsn = jumlahAsn.toString())
            )
        }

        dashboardViewModel.roda3.observe(this){
            var jumlah = 0
            var jumlahAsn = 0
            it.forEach {
                jumlah++
                if(it.nipAsn.toInt()!=0){
                    jumlahAsn++
                }
            }

            dashboardViewModel.insertRekapData(RekapDataKendaraan(
                id = "2",
                instansi = it.get(0).instansiId,
                jenis_roda = "Roda 3",
                jumlah = jumlah.toString(),
                jumlahAsn = jumlahAsn.toString())
            )
        }

        dashboardViewModel.roda4.observe(this){
            var jumlah = 0
            var jumlahAsn = 0
            it.forEach {
                jumlah++
                if(it.nipAsn.toInt()!=0){
                    jumlahAsn++
                }
            }

            dashboardViewModel.insertRekapData(RekapDataKendaraan(
                id = "3",
                instansi = it.get(0).instansiId,
                jenis_roda = "Roda 4",
                jumlah = jumlah.toString(),
                jumlahAsn = jumlahAsn.toString())
            )
        }
    }
}
