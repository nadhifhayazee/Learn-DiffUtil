package com.nadhif.hayazee.learndiffutil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.nadhif.hayazee.learndiffutil.databinding.UserItemLayoutBinding

class WithDiffUtilAdapter(private val viewModel: MainViewModel) :
    ListAdapter<User, WithDiffUtilAdapter.ViewHolder>(userDiffUtilCallback) {

    companion object {
        val userDiffUtilCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
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

    override fun submitList(list: MutableList<User>?) {
        super.submitList(list?.map { it.copy() })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            UserItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}