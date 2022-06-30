package com.example.mol12345

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val viewpagerFragmentAdapter = ViewpagerFragmentAdapter(this)

        viewPager.adapter = viewpagerFragmentAdapter

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        val tabTitles = listOf<String>("Contact", "Gallery", "Calculator")

        TabLayoutMediator(tabLayout, viewPager) { tab, position -> tab.text = tabTitles[position] }.attach()
    }

}

class ViewpagerFragmentAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf<Fragment>(Fragment01(), Fragment02(), Fragment03())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}