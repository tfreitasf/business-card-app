package me.dio.businesscard.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import me.dio.businesscard.data.BusinessCard
import me.dio.businesscard.databinding.ItemBusinessCardBinding

class BusinessCardApapter : ListAdapter<BusinessCard, BusinessCardApapter.ViewHolder>(DiffCallBack()) {

    var listinerShare:(View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemBusinessCardBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind (item: BusinessCard){
            binding.tvName.text = item.nome
            binding.tvCompany.text = item.empresa
            binding.tvEmail.text = item.email
            binding.tvPhone.text = item.telefone
            binding.mcvContent.setCardBackgroundColor(Color.parseColor(item.backgroundcolor))
            binding.mcvContent.setOnClickListener{
                listinerShare(it)
            }
        }
    }


}

class DiffCallBack: DiffUtil.ItemCallback<BusinessCard>(){
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard)= oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem.id == newItem.id
}

