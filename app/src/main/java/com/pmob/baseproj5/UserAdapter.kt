package com.pmob.baseproj5

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pmob.baseproj5.databinding.ItemUserBinding

class UserAdapter(private val listUser: List<DataUser>) :
	RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

	inner class UserViewHolder(val binding: ItemUserBinding) :
		RecyclerView.ViewHolder(binding.root)

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
		val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return UserViewHolder(binding)
	}

	override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
		val user = listUser[position]
		with(holder.binding) {
			tvUsername.text = user.username
			tvCaption.text = user.caption
			if (!user.imageUri.isNullOrEmpty()) {
				imgUser.setImageURI(Uri.parse(user.imageUri))
			}
		}
	}

	override fun getItemCount(): Int = listUser.size
}
