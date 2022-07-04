package com.example.mol12345

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mol12345.databinding.ContactsBinding

class ContactActivity : AppCompatActivity() {
    lateinit var binding : ContactsBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                if(it.resultCode == RESULT_OK){
                    binding.humanName.text = it.data?.getStringExtra("edit_human_name")
                    binding.humanNickname.text = it.data?.getStringExtra("edit_human_nick")
                    binding.phoneNumber.text = it.data?.getStringExtra("edit_phone_number")
                }
            }

        val humanResource : Bundle? = intent.extras
        if (humanResource == null){
            id = 0
            binding.humanName.text = "Not found"
            binding.humanNickname.text = "Not found"
            binding.phoneNumber.text = "010-0000-0000"
        }
        else{
            id = humanResource.getInt("id")
            binding.humanName.text = humanResource.getString("Human_name")
            binding.humanNickname.text = humanResource.getString("Human_nick")
            binding.phoneNumber.text = humanResource.getString("Phone_number")

            binding.calling.setOnClickListener {
                Toast.makeText(this,"전화 연결",
                Toast.LENGTH_SHORT).show()
                val number : String = "tel:${binding.phoneNumber.text.toString()}"
                val intent : Intent = Intent(Intent.ACTION_VIEW,Uri.parse(number))
                startActivity(intent)
            }

            binding.sendMessage.setOnClickListener {
                Toast.makeText(this,"메세지 전송",
                Toast.LENGTH_SHORT).show()
                val number : String = "smsto:${binding.phoneNumber.text.toString()}"
                val intent : Intent = Intent(Intent.ACTION_SENDTO, Uri.parse(number))
                startActivity(intent)
            }

            binding.edit.setOnClickListener {//edit
                val intent = Intent(this, EditContactsActivity::class.java)
                intent.putExtra("edit_id",id)
                intent.putExtra("edit_human_name",binding.humanName.text.toString())
                intent.putExtra("edit_human_nick",binding.humanNickname.text.toString())
                intent.putExtra("edit_phone_number",binding.phoneNumber.text.toString())
                activityResultLauncher.launch(intent)
            }
        }
    }
}