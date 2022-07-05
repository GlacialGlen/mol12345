package com.example.mol12345

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private val permissionList = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )
    private val checkPermission = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
        result.forEach {
            if(!it.value) {
                Toast.makeText(applicationContext, "${it.key} not granted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        checkPermission.launch(permissionList)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val viewpagerFragmentAdapter = ViewpagerFragmentAdapter(this)

        viewPager.adapter = viewpagerFragmentAdapter

        val tabLayout: TabLayout = findViewById(R.id.tab_layout)

        val tabIcon = listOf(R.drawable.calicon,R.drawable.galicon, R.drawable.calculatoricon)

        TabLayoutMediator(tabLayout, viewPager) { tab, position -> tab.icon = AppCompatResources.getDrawable(this, tabIcon[position]) }.attach()
    }

}

class ViewpagerFragmentAdapter(fragmentActivity: FragmentActivity)
    : FragmentStateAdapter(fragmentActivity) {

    private val fragmentList = listOf(Fragment01(), Fragment02(), Fragment03())

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }
}