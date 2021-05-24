package com.example.sammary3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.sammary3.databinding.ItemLayoutBinding

class UserAdapter(private val usersList: MutableList<User>, val listener: UserItemListener) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val binding =
            ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind()
    }                                                                               

    inner class ViewHolder(private val binding: ItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = usersList[adapterPosition]

            binding.tvFirstName.text = model.firstName
            binding.tvLastName.text = model.lastName
            binding.tvEmail.text = model.email

            binding.btnUpdate.setOnClickListener {
                listener.updateUser(adapterPosition)
            }

            binding.btnDelete.setOnClickListener {
                listener.deleteUser(adapterPosition)
            }
        }
    }
}