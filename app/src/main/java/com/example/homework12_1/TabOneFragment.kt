package com.example.homework12_1

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.homework12_1.data.JsonClass
import com.example.homework12_1.data.JsonClassItem
import com.google.gson.GsonBuilder


class TabOneFragment : Fragment(), MyAdapter.MyViewHolder.ClickListener {

    private lateinit var newRecyclerView: RecyclerView
    private var newArrayList: ArrayList<RecycleData> = arrayListOf<RecycleData>()
    lateinit var imageId: Array<Int>
    lateinit var descript: Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_one, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onResume() {
        super.onResume()

        newRecyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)!!
        newRecyclerView.layoutManager = LinearLayoutManager(activity)
        newRecyclerView.addItemDecoration(DividerItemDecoration(activity, 1))

        val sharPreferences = activity?.getSharedPreferences("main", Context.MODE_PRIVATE)
        var resJson = sharPreferences?.getString("image", "0")


        if (resJson != "0") {

            newArrayList.clear()
            val gson = GsonBuilder().create()
            val data = gson.fromJson<JsonClass>(resJson, JsonClass::class.java)

            for (i in 0..data.lastIndex) {
                val item = RecycleData(data[i].description.toString(), data[i].path.toString())
                newArrayList.add(item)
            }
            newRecyclerView.adapter = MyAdapter(newArrayList, this)

        }


    }

    override fun onItemClick(item: RecycleData, position: Int) {
        //  Toast.makeText(activity, "item $position" , Toast.LENGTH_SHORT).show()
        val sharPreferences = activity?.getSharedPreferences("main", Context.MODE_PRIVATE)
        var resJson = sharPreferences?.getString("image", "0")

        if (resJson != "0") {
            val gson = GsonBuilder().create()
            val data = gson.fromJson<JsonClass>(resJson, JsonClass::class.java)

            if (activity != null) {
                val intent = Intent(activity, PhotoView::class.java)
                intent.putExtra("Description", data[position].description)
                intent.putExtra("Path", data[position].path)
                startActivity(intent)
            }


        }

    }

}

