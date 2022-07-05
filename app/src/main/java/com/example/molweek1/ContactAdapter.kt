package com.example.molweek1

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.molweek1.databinding.ItemRecyclerBinding

data class Data1(
    var id : Int,
    var name : String,
    var nick : String,
    var number : String,
)

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.MyViewHolder>(){
    var data = mutableListOf<Data1>()
    private var _bb : ItemRecyclerBinding? = null
    private val bbinding get() = _bb!!
    private lateinit var sharedManager: SharedManager
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        _bb = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val binding = bbinding
        sharedManager = SharedManager(binding.name.context)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }
    inner class MyViewHolder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(layoutData: Data1) {
            binding.name.text = layoutData.name
            val context = binding.name.context
            binding.name.setOnClickListener {
                val intent = Intent(context, ContactActivity::class.java)
                intent.putExtra("id",layoutData.id)
                intent.putExtra("Human_name",layoutData.name)
                intent.putExtra("Human_nick",layoutData.nick)
                intent.putExtra("Phone_number",layoutData.number)
                context.startActivity(intent)
            }
        }
    }
}