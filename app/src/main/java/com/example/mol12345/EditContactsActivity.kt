package com.example.mol12345

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mol12345.databinding.EditContanctsBinding

class EditContactsActivity : AppCompatActivity() {
    private lateinit var binding : EditContanctsBinding
    private lateinit var sharedManager: SharedManager
    private var id : Int = 0
    private var flag : Int = -1
    private lateinit var name : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditContanctsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedManager = SharedManager(this)
        val editorResource : Bundle? = intent.extras
        if(editorResource == null){
            binding.editHumanName.setText(getString(R.string.contact_not_found))
            binding.editHumanNickname.setText(R.string.contact_not_found)
            binding.editPhoneNumber.setText(R.string.human_phone_num)
        }
        else {
            id = editorResource.getInt("edit_id")
            flag = editorResource.getInt("flag")
            binding.editHumanName.setText(editorResource.getString("edit_human_name"))
            name = editorResource.getString("edit_human_name").toString()
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
            if(flag == 0) {
                val intent = Intent(this, ContactActivity::class.java)
                intent.putExtra("edit_human_name", binding.editHumanName.text.toString())
                intent.putExtra("edit_human_nick", binding.editHumanNickname.text.toString())
                intent.putExtra("edit_phone_number", binding.editPhoneNumber.text.toString())
                sharedManager.saveCurrentData(
                    Data1(
                        id = id,
                        name = binding.editHumanName.text.toString(),
                        nick = binding.editHumanNickname.text.toString(),
                        number = binding.editPhoneNumber.text.toString()
                    )
                )
                setResult(Activity.RESULT_OK, intent)
                if (!isFinishing) finish()
            }
            else if(flag == 1){
                val intent = Intent(this, Fragment01::class.java)
                intent.putExtra("edit_human_name", binding.editHumanName.text.toString())
                intent.putExtra("edit_human_nick", binding.editHumanNickname.text.toString())
                intent.putExtra("edit_phone_number", binding.editPhoneNumber.text.toString())
                sharedManager.saveCurrentData(
                    Data1(
                        id = id,
                        name = binding.editHumanName.text.toString(),
                        nick = binding.editHumanNickname.text.toString(),
                        number = binding.editPhoneNumber.text.toString()
                    )
                )
                setResult(Activity.RESULT_OK, intent)
                if (!isFinishing) finish()

            }
            else{
                finish()
            }
        }
        binding.back.setOnClickListener {
            if(!isFinishing) finish()
        }
        binding.delete.setOnClickListener {
            val intent = Intent(this, Fragment01::class.java)
            sharedManager.saveCurrentData(
                Data1(
                    id = id,
                    name = "delete",
                    nick = "aaa",
                    number = "aaaa"
                )
            )
            setResult(Activity.RESULT_FIRST_USER, intent)
            Toast.makeText(this,"${name}님의 연락처가 삭제 되었습니다",Toast.LENGTH_SHORT).show()
            if (!isFinishing) finish()

        }
    }
}