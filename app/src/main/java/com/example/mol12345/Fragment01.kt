package com.example.mol12345

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mol12345.databinding.Fragment01Binding
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import org.json.JSONException
import java.io.*
import java.lang.NullPointerException


class Fragment01: Fragment() {
    private lateinit var profileAdapter: ContactAdapter
    private val dataList: MutableList<Data1> = mutableListOf()
    private var idNum :Int = 1
    private var _binding : Fragment01Binding? = null
    private val binding get() = _binding!!
    private val gson = Gson()
    private lateinit var sharedManager: SharedManager
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = Fragment01Binding.inflate(inflater,container,false)
        val view = binding.root
        sharedManager = SharedManager(view.context)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                if(it.resultCode == RESULT_OK){
                        val d = Data1(
                            id = idNum++,
                            name = it.data?.getStringExtra("edit_human_name") ?: "name",
                            nick = it.data?.getStringExtra("edit_human_nick") ?: "nickname",
                            number = it.data?.getStringExtra("edit_phone_number") ?: "010-0000-0000"
                        )
                        dataList.add(d)
                        profileAdapter.notifyDataSetChanged()
                }
            }

        profileAdapter = ContactAdapter()
        profileAdapter.data = dataList

        readContactsFromJson()
        if (dataList.isNotEmpty()) {
            binding.noContactImage.visibility = View.INVISIBLE
            binding.noContactText.visibility = View.INVISIBLE
        }
        binding.rvProfile.adapter = profileAdapter
        binding.rvProfile.layoutManager = LinearLayoutManager(view.context)
        binding.rvProfile.addItemDecoration(VerticalItemDecorator(20))
        binding.rvProfile.addItemDecoration(HorizontalItemDecorator(10))
        binding.fab.setOnClickListener{
            val intent = Intent(binding.rvProfile.context, EditContactsActivity::class.java)
            intent.putExtra("edit_id",idNum)
            intent.putExtra("flag",1)
            intent.putExtra("edit_human_name","박강우")
            intent.putExtra("edit_human_nick","박 강 우")
            intent.putExtra("edit_phone_number","010-0000-0000")
            activityResultLauncher.launch(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        val data1 : Data1 = sharedManager.getCurrentData()
        for (i in dataList) {
            if (i.id == data1.id) {
                if(data1.name == "delete"){
                    dataList.remove(i)

                }
                else {
                    i.name = data1.name
                    i.nick = data1.nick
                    i.number = data1.number
                }
                break
            }
        }
        if (dataList.isNotEmpty()) {
            binding.noContactImage.visibility = View.INVISIBLE
            binding.noContactText.visibility = View.INVISIBLE
        }
        else {
            binding.noContactImage.visibility = View.VISIBLE
            binding.noContactText.visibility = View.VISIBLE
        }
        profileAdapter.notifyDataSetChanged()
    }

    private fun readContactsFromJson() {
        var jsonString = ""
        try {
            val jsonFile = File(context?.filesDir, "contacts.json")
            jsonFile.createNewFile()
            val inputStream = context?.openFileInput("contacts.json")

            if (inputStream != null) {
                val inputStreamReader = InputStreamReader(inputStream)
                val bufferedReader = BufferedReader(inputStreamReader)
                var receiveString: String?
                val stringBuilder = StringBuilder()
                while (bufferedReader.readLine().also { receiveString = it } != null) {
                    stringBuilder.append(receiveString)
                }
                jsonString = stringBuilder.toString()
                inputStream.close()
            }

            val itemType = object : TypeToken<MutableList<String>>() {}.type
            try {
                val data: MutableList<String> = gson.fromJson(jsonString, itemType)
                for (d in data) {
                    dataList.add(gson.fromJson(d, Data1::class.java))
                }
                if (dataList.isNotEmpty()) {
                    idNum = dataList[dataList.lastIndex].id + 1
                }
            }
            catch (_: NullPointerException) {
                Toast.makeText(context, "No contacts now.", Toast.LENGTH_SHORT).show()
            }

        }
        catch (e: IOException) {
            e.printStackTrace()
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }

        profileAdapter.data = dataList
    }

    private fun writeContactsToJson() {
        val jsonFile = File(context?.filesDir, "contacts.json")
        jsonFile.delete()
        jsonFile.createNewFile()
        try {
            val fileWriter = FileWriter(jsonFile)
            val jsonTotalObject = JsonArray()

            for (data in dataList) {
                val jsonEntry = gson.toJson(data)
                jsonTotalObject.add(jsonEntry)
            }
            fileWriter.append(jsonTotalObject.toString())
            fileWriter.flush()
            fileWriter.close()
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun onPause() {
        writeContactsToJson()
        super.onPause()
    }

}
class VerticalItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration() {
    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.top = divHeight
        outRect.bottom = divHeight
    }
}
class HorizontalItemDecorator(private val divHeight : Int) : RecyclerView.ItemDecoration() {

    @Override
    override fun getItemOffsets(outRect: Rect, view: View, parent : RecyclerView, state : RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = divHeight
        outRect.right = divHeight
    }
}