package com.geco.bpadapp.ui.kendaraan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geco.bpadapp.data.models.Kendaraan
import com.geco.bpadapp.databinding.ItemKendaraanInstansiBinding

class KendaraanAdapter(
    private var kendaraanList: List<Kendaraan>
) : RecyclerView.Adapter<KendaraanAdapter.KendaraanViewHolder>() {

    // ViewHolder untuk setiap item di RecyclerView
    class KendaraanViewHolder(private val binding: ItemKendaraanInstansiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(kendaraan: Kendaraan) {
            binding.tvNama.text = kendaraan.tipe
            binding.tvNopol.text = kendaraan.nopol
            binding.tvJenisKendaraan.text = kendaraan.jenisRoda
            binding.tvMerek.text = kendaraan.merek
            binding.tvPenanggungJawab.text = kendaraan.nipAsn
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KendaraanViewHolder {
        val binding =
            ItemKendaraanInstansiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KendaraanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: KendaraanViewHolder, position: Int) {
        holder.bind(kendaraanList[position])
    }

    override fun getItemCount(): Int = kendaraanList.size
}
