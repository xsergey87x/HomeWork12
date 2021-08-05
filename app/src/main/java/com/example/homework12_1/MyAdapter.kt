package com.example.homework12_1

import android.content.Context
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import java.io.File

class MyAdapter(
    private val RecycleDataList: ArrayList<RecycleData>,
    var clickListner: MyViewHolder.ClickListener
) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item,
            parent, false
        )
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = RecycleDataList[position]

        holder.initialize(RecycleDataList.get(position), clickListner)

        holder.Description.text = currentItem.describePfoto
        Glide.with(holder.FotoIcon.context).load(RecycleDataList[position].fotoData)
            .override(50, 50).into(holder.FotoIcon)
    }

    override fun getItemCount(): Int {

        return RecycleDataList.size
    }


    fun getItem(position: Int): String? {
        return RecycleDataList?.get(position).toString()
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val FotoIcon: ShapeableImageView = itemView.findViewById(R.id.PfotoIcon)
        val Description: TextView = itemView.findViewById(R.id.description)


        fun initialize(item: RecycleData, action: ClickListener) {


            itemView.setOnClickListener {
                action.onItemClick(item, adapterPosition)
            }


        }

        interface ClickListener {
            fun onItemClick(item: RecycleData, position: Int)
        }
    }
}


