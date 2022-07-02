package com.example.mol12345

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mol12345.databinding.ContactsBinding

class ContactActivity : AppCompatActivity() {
    lateinit var binding : ContactsBinding
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val humanResource = intent.extras
        if (humanResource == null){
            binding.humanName.text = "Not found"
            binding.humanNickname.text = "Not found"
            binding.phoneNumber.text = "010-0000-0000"
        }
        else{
            binding.humanName.text = humanResource.getString("Human_name")
            binding.humanNickname.text = humanResource.getString("Human_nick")
            binding.phoneNumber.text = humanResource.getString("Phone_number")
            binding.calling.setOnClickListener {
                Toast.makeText(this,"전화 연결",
                Toast.LENGTH_SHORT).show()
            }
            binding.sendMessage.setOnClickListener {
                Toast.makeText(this,"메세지 전송",
                Toast.LENGTH_SHORT).show()
            }
        }


    }
}