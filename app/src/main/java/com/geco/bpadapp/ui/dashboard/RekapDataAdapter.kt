package com.geco.bpadapp.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.geco.bpadapp.data.models.RekapDataKendaraan
import com.geco.bpadapp.databinding.ItemRekapDataInstansiBinding

class RekapDataAdapter(
    private var rekapDataKendaraan: List<RekapDataKendaraan>,
    private val onItemClick: (RekapDataKendaraan) -> Unit
) : RecyclerView.Adapter<RekapDataAdapter.RekapDataKendaraanViewHolder>() {

    class RekapDataKendaraanViewHolder(private val binding: ItemRekapDataInstansiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(rekapDataKendaraan: RekapDataKendaraan, onItemClick: (RekapDataKendaraan) -> Unit) {
            binding.tvNamaInstansi.text = rekapDataKendaraan.instansi
            binding.tvJenisRoda.text = rekapDataKendaraan.jenis_roda
            binding.tvJumlahKendaraan.text = "Jumlah Kendaraan : ${rekapDataKendaraan.jumlah}"
            binding.tvJumlahKendaraan.text = "Jumlah ASN Penanggung Jawab : ${rekapDataKendaraan.jumlahAsn}"

            binding.root.setOnClickListener { onItemClick(rekapDataKendaraan) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RekapDataKendaraanViewHolder {
        val binding =
            ItemRekapDataInstansiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RekapDataKendaraanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RekapDataKendaraanViewHolder, position: Int) {
        holder.bind(rekapDataKendaraan[position]){onItemClick(it)}
    }

    override fun getItemCount(): Int = rekapDataKendaraan.size
}
