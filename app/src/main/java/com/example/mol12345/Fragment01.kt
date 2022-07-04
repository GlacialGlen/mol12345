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
    private val datas : MutableList<Data1> = mutableListOf((Data1(id = 1,name = "박강우", nick = "강우", number = "010-0110-1111"))
            ,(Data1(id = 2,name = "김성혁", nick = "그림 장인 김화백", number = "010-0020-1111"))
            ,(Data1(id = 3,name = "김성애", nick = "사랑둥이", number = "010-0250-1111"))
            ,(Data1(id = 4,name = "김기현", nick = "술꾼", number = "010-0570-1111"))
            ,(Data1(id = 5,name = "김예은", nick = "너구리", number = "010-0690-1111"))
            ,(Data1(id = 6,name = "변경호", nick = "경호", number = "010-0100-1111")))
    private var id_num :Int = 7
    private var _binding : Fragment01Binding? = null
    private val binding get() = _binding!!
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = Fragment01Binding.inflate(inflater,container,false)
        val view = binding.root
//        activityResultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//                if(it.resultCode == RESULT_OK){
//
//                }
//            }
        profileAdapter = ContactAdapter()
        profileAdapter.data = datas
        binding.rvProfile.adapter = profileAdapter
        binding.rvProfile.layoutManager = LinearLayoutManager(view.context)
        binding.rvProfile.addItemDecoration(VerticalItemDecorator(20))
        binding.rvProfile.addItemDecoration(HorizontalItemDecorator(10))
        binding.fab.setOnClickListener{
            datas.add(Data1(id = id_num++,name = "박강우", nick = "박 강 우", number = "010-0000-0000"))
            profileAdapter?.notifyDataSetChanged()
        }

        return view
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