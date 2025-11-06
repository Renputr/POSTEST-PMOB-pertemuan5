package com.pmob.baseproj5.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pmob.baseproj5.R
import com.pmob.baseproj5.data.DataUser

class UserAdapter(private val dataList: List<DataUser>) :
	RecyclerView.Adapter<UserAdapter.ViewHolder>() {

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val nama: TextView = view.findViewById(R.id.tvNama)
		val deskripsi: TextView = view.findViewById(R.id.tvDeskripsi)
		val tanggal: TextView = view.findViewById(R.id.tvTanggal)
		val gambar: ImageView = view.findViewById(R.id.ivGambar)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.item_user, parent, false)
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val user = dataList[position]
		holder.nama.text = user.nama
		holder.deskripsi.text = user.deskripsi
		holder.tanggal.text = user.tanggal
		// Untuk gambar nanti kamu bisa load pakai Glide/Picasso
	}

	override fun getItemCount(): Int = dataList.size
}
