package com.example.homework12_1

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.homework12_1.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2
    private lateinit var newArrayList:ArrayList<RecycleData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            viewPager2 = findViewById(R.id.view_pager)
            viewPager2.adapter = SampleAdapter(this)



        /*    val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)  */

        }
    }


class SampleAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> TabOneFragment()
            1 -> TabTwoFragment()
            else -> throw IllegalArgumentException("Only 2 tabs")
        }
    }

}