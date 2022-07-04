package com.example.mol12345

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mol12345.databinding.EditContanctsBinding

class EditContactsActivity : AppCompatActivity() {
    private lateinit var binding : EditContanctsBinding
    private lateinit var sharedManager: SharedManager
    private var id : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditContanctsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedManager = SharedManager(this)
        val editorResource : Bundle? = intent.extras
        if(editorResource == null){
            binding.editHumanName.setText(getString(R.string.contact_not_found))
            binding.editHumanNickname.setText(R.string.contact_not_found)
            binding.editPhoneNumber.setText(R.string.humanpnum)
        }
        else {
            id = editorResource.getInt("edit_id")
            binding.editHumanName.setText(editorResource.getString("edit_human_name"))
            binding.editHumanNickname.setText(editorResource.getString("edit_human_nick"))
            binding.editPhoneNumber.setText(editorResource.getString("edit_phone_number"))
        }

        binding.editHumanName.setOnEditorActionListener{
                _, _, _ -> false
        }
        binding.editHumanNickname.setOnEditorActionListener{
                _, _, _ -> false
        }
        binding.editPhoneNumber.setOnEditorActionListener {
                _, _, _ -> false
        }
        binding.store.setOnClickListener {
            val intent = Intent(this, ContactActivity::class.java)
            intent.putExtra("edit_human_name", binding.editHumanName.text.toString())
            intent.putExtra("edit_human_nick", binding.editHumanNickname.text.toString())
            intent.putExtra("edit_phone_number", binding.editPhoneNumber.text.toString())
            sharedManager.saveCurrentData(Data1(id = id, name = binding.editHumanName.text.toString(),
                nick = binding.editHumanNickname.text.toString(), number = binding.editPhoneNumber.text.toString()))
            setResult(Activity.RESULT_OK, intent)
            if(!isFinishing) finish()
        }
        binding.back.setOnClickListener {
            if(!isFinishing) finish()
        }
    }
}