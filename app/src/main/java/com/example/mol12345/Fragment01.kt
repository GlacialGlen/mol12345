package com.example.mol12345

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mol12345.databinding.Fragment01Binding
import com.example.mol12345.databinding.ItemRecyclerBinding

class Fragment01: Fragment() {
    lateinit var profileAdapter: fragment01Adapter
    private lateinit var recyclerView : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_01, container, false)
        recyclerView = view.findViewById(R.id.rv_profile)
        profileAdapter = fragment01Adapter()
        profileAdapter.data.apply{
            add(Data1(name = "박강우", number = "010-0000-1111"))
            add(Data1(name = "박강우", number = "010-0000-1111"))
            add(Data1(name = "박강우", number = "010-0000-1111"))
            add(Data1(name = "박강우", number = "010-0000-1111"))
            add(Data1(name = "박강우", number = "010-0000-1111"))
            add(Data1(name = "박강우", number = "010-0000-1111"))
        }
        recyclerView.adapter = profileAdapter
        recyclerView.addItemDecoration(VerticalItemDecorator(20))
        recyclerView.addItemDecoration(HorizontalItemDecorator(10))

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