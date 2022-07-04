package com.example.mol12345

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mol12345.databinding.Fragment01Binding
import com.example.mol12345.databinding.ItemRecyclerBinding

class Fragment01: Fragment() {
    lateinit var profileAdapter: ContactAdapter
    private val datas : MutableList<Data1> = mutableListOf(
             (Data1(id = 1,name = "박강우", nick = "강우", number = "010-0110-1111"))
            ,(Data1(id = 2,name = "김성혁", nick = "그림 장인 김화백", number = "010-0020-1111"))
            ,(Data1(id = 3,name = "김성애", nick = "사랑둥이", number = "010-0250-1111"))
            ,(Data1(id = 4,name = "김기현", nick = "술꾼", number = "010-0570-1111"))
            ,(Data1(id = 5,name = "김예은", nick = "너구리", number = "010-0690-1111"))
            ,(Data1(id = 6,name = "변경호", nick = "경호", number = "010-0100-1111")))
    private var id_num :Int = 7
    private var _binding : Fragment01Binding? = null
    private val binding get() = _binding!!
    private lateinit var sharedManager: SharedManager
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment01Binding.inflate(inflater,container,false)
        val view = binding.root
        sharedManager = SharedManager(view.context)

        activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            {
                if(it.resultCode == RESULT_OK){
                    var d = Data1(id = id_num++, name = it.data?.getStringExtra("edit_human_name")?:"name",
                        nick = it.data?.getStringExtra("edit_human_nick")?:"nickname",
                        number = it.data?.getStringExtra("edit_phone_number")?:"010-0000-0000")
                    datas.add(d)
                    profileAdapter?.notifyDataSetChanged()
                }
            }

        profileAdapter = ContactAdapter()
        profileAdapter.data = datas
        binding.rvProfile.adapter = profileAdapter
        binding.rvProfile.layoutManager = LinearLayoutManager(view.context)
        binding.rvProfile.addItemDecoration(VerticalItemDecorator(20))
        binding.rvProfile.addItemDecoration(HorizontalItemDecorator(10))
        binding.fab.setOnClickListener{
            val intent = Intent(binding.rvProfile.context, EditContactsActivity::class.java)
            intent.putExtra("edit_id",id)
            intent.putExtra("edit_human_name","박강우")
            intent.putExtra("edit_human_nick","박 강 우")
            intent.putExtra("edit_phone_number","010-0000-0000")
            activityResultLauncher.launch(intent)
        }

        return view
    }

    override fun onResume() {
        super.onResume()
        var data1 : Data1 = sharedManager.getCurrentData()
        for(i in datas){
            if(i.id == data1.id){
                i.name = data1.name
                i.nick = data1.nick
                i.number = data1.number
            }
        }
        profileAdapter?.notifyDataSetChanged()
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