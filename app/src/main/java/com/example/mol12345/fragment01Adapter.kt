package com.example.mol12345

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mol12345.databinding.Fragment01Binding
import com.example.mol12345.databinding.ItemRecyclerBinding

data class Data1(
    val name : String,
    val number : String,
)

class fragment01Adapter : RecyclerView.Adapter<fragment01Adapter.MyViewHolder>(){
    var data = mutableListOf<Data1>()
    lateinit var pa:ViewGroup
    private var _bb : ItemRecyclerBinding? = null
    private val bbinding get() = _bb!!
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _bb = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val binding = bbinding//ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,parent,false)
        pa = parent
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }
    inner class MyViewHolder(val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
//        init {
//
//        }//check
        fun bind(layoutData: Data1) {
            binding.tvRvName.text = layoutData.name
            binding.tvRvNumber.text = layoutData.number
            binding.call.setOnClickListener {M1()}
            binding.message.setOnClickListener {M2()}
        }
        fun M1(){
            Toast.makeText(pa.context,"calling"
            ,Toast.LENGTH_SHORT).show()
        }

        fun M2(){
            Toast.makeText(pa.context,"sending message"
                ,Toast.LENGTH_SHORT).show()
        }
    }


}