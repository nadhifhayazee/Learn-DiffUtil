package com.nadhif.hayazee.learndiffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nadhif.hayazee.learndiffutil.databinding.UserItemLayoutBinding

class NoDiffUtilAdapter(private val viewModel: MainViewModel) : RecyclerView.Adapter<NoDiffUtilAdapter.ViewHolder>() {

    private var listUser: MutableList<User> = mutableListOf()

    fun setData(list: MutableList<User>) {
        listUser.removeAll(listUser)
        listUser.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(listUser.get(position))
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


    inner class ViewHolder(private val binding: UserItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindView(user: User) {
            binding.apply {
                tvName.text = user.name
                tvPhone.text = user.phone
                if (user.isSelected){
                    root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.teal_200))
                } else {
                    root.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.white))
                }
                btnDelete.setOnClickListener { viewModel.deleteUser(user) }
                root.setOnClickListener { viewModel.setSelectedUser(user) }
            }
        }

    }

}