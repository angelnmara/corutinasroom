package com.lamarrulla.examentecnicoandroid.ui.contact

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.lamarrulla.examentecnicoandroid.data.model.Contact

import com.lamarrulla.examentecnicoandroid.placeholder.PlaceholderContent.PlaceholderItem
import com.lamarrulla.examentecnicoandroid.databinding.FragmentContactBinding

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MyConcactRecyclerViewAdapter(
    private val values: List<Contact>, private val context: Context, private val listener:(Contact)->Unit
) : RecyclerView.Adapter<MyConcactRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.idView.text = item.name
        holder.contentView.text = item.phone
        Glide.with(context).load(item.image).circleCrop().into(holder.imageView)
        holder.itemView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.txtNameContact
        val contentView: TextView = binding.txtPhoneContact
        val imageView:ImageView = binding.imgContact

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}