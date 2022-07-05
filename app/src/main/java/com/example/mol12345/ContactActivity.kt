package com.example.mol12345

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.mol12345.databinding.ContactsBinding

class ContactActivity : AppCompatActivity() {
    private lateinit var binding : ContactsBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var id : Int = 0

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                if(it.resultCode == RESULT_OK){
                    binding.humanName.text = it.data?.getStringExtra("edit_human_name")
                    binding.humanNickname.text = it.data?.getStringExtra("edit_human_nick")
                    binding.phoneNumber.text = it.data?.getStringExtra("edit_phone_number")
                }
                else if(it.resultCode == RESULT_FIRST_USER){
                    if(!isFinishing) finish()
                }
            }

        val humanResource : Bundle? = intent.extras
        if (humanResource == null){
            id = 0
            binding.humanName.text = getString(R.string.contact_not_found)
            binding.humanNickname.text = getString(R.string.contact_not_found)
            binding.phoneNumber.text = getString(R.string.humanpnum)
        }
        else{
            id = humanResource.getInt("id")
            binding.humanName.text = humanResource.getString("Human_name")
            binding.humanNickname.text = humanResource.getString("Human_nick")
            binding.phoneNumber.text = humanResource.getString("Phone_number")

            binding.calling.setOnClickListener {
                Toast.makeText(this,"전화 연결",
                Toast.LENGTH_SHORT).show()
                val number = "tel:${binding.phoneNumber.text}"
                val intent = Intent(Intent.ACTION_VIEW,Uri.parse(number))
                startActivity(intent)
            }

            binding.sendMessage.setOnClickListener {
                Toast.makeText(this,"메세지 전송",
                Toast.LENGTH_SHORT).show()
                val number = "smsto:${binding.phoneNumber.text}"
                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse(number))
                startActivity(intent)
            }

            binding.edit.setOnClickListener {//edit
                val intent = Intent(this, EditContactsActivity::class.java)
                intent.putExtra("flag",0)
                intent.putExtra("edit_id",id)
                intent.putExtra("edit_human_name",binding.humanName.text.toString())
                intent.putExtra("edit_human_nick",binding.humanNickname.text.toString())
                intent.putExtra("edit_phone_number",binding.phoneNumber.text.toString())
                activityResultLauncher.launch(intent)
            }
        }
    }
}