package com.example.mol12345

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mol12345.databinding.EditContanctsBinding

class EditContactsActivity : AppCompatActivity() {
    lateinit var  binding : EditContanctsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditContanctsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editorResource : Bundle? = intent.extras
        if(editorResource == null){
            binding.editHumanName.setText("Not found")
            binding.editHumanNickname.setText("Not found")
            binding.editPhoneNumber.setText("010-0000-0000")
        }
        else {
            binding.editHumanName.setText(editorResource.getString("edit_human_name"))
            binding.editHumanNickname.setText(editorResource.getString("edit_human_nick"))
            binding.editPhoneNumber.setText(editorResource.getString("edit_phone_number"))

        }

        binding.editHumanName.setOnEditorActionListener{
                v, actionId, event -> false
        }
        binding.editHumanNickname.setOnEditorActionListener{
                v, actionId, event -> false
        }
        binding.editPhoneNumber.setOnEditorActionListener {
                v, actionId, event -> false
        }
        binding.store.setOnClickListener {
            val intent : Intent = Intent(this, ContactActivity::class.java)
            intent.putExtra("edit_human_name", binding.editHumanName.text.toString())
            intent.putExtra("edit_human_nick", binding.editHumanNickname.text.toString())
            intent.putExtra("edit_phone_number", binding.editPhoneNumber.text.toString())
            setResult(Activity.RESULT_OK, intent)
            if(!isFinishing) finish()
        }
        binding.back.setOnClickListener {
            if(!isFinishing) finish()
        }


    }
}