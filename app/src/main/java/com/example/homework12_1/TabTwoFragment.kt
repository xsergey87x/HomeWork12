package com.example.homework12_1

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.homework12_1.data.JsonClass
import com.google.android.material.textfield.TextInputLayout
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class TabTwoFragment : Fragment() {

    private val authority = "com.example.homework12_1.fileprovider"
    private var currentPhotoPath: String? = null
    private val contract = ActivityResultContracts.StartActivityForResult()
    private val resultLauncher = registerForActivityResult(contract) {
        if (it.resultCode == RESULT_OK) {
            Glide.with(this).load(currentPhotoPath).into(imageView);
        }

    }

    var tutsArray: ArrayList<JSON_Data> = arrayListOf<JSON_Data>()
    lateinit var imageView: ImageView
    var resJson: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab_two, container, false)
    }

    override fun onResume() {
        super.onResume()


        val description = view?.findViewById<TextInputLayout>(R.id.descriptionText)
        imageView = view?.findViewById(R.id.imageView)!!

        view?.findViewById<Button>(R.id.BtFoto)?.setOnClickListener {
            resJson = takePhoto()
        }

        view?.findViewById<Button>(R.id.BtSave)?.setOnClickListener {
            if (description?.editText?.text.isNullOrEmpty()) {
                description?.error = "Description could not be empty"
            } else {
                description?.error = null
                if (resJson != "") {
                    savePhoto(description?.editText?.text.toString(), resJson)
                }
            }
        }
    }

    private fun savePhoto(descript: String, path: String) {

        val sharPreferences = activity?.getSharedPreferences("main", Context.MODE_PRIVATE)
        var oldJson = sharPreferences?.getString("image", "0")

        if (oldJson != "0") {

            tutsArray.clear()
            val gson = GsonBuilder().create()
            val data = gson.fromJson<JsonClass>(oldJson, JsonClass::class.java)

            for (i in 0..data.lastIndex) {
                val item = JSON_Data(data[i].description.toString(), data[i].path.toString())
                tutsArray.add(item)
            }

        }

        val gson = Gson()
        var itemm = JSON_Data(descript, path)

        tutsArray.add(itemm)
        var resultJson = gson.toJson(tutsArray)

        sharPreferences?.edit()?.putString("image", resultJson.toString())?.apply()
        Toast.makeText(activity, "Photo saved successfully" , Toast.LENGTH_SHORT).show()

    }

    @SuppressLint("SimpleDateFormat")
    private fun takePhoto(): String {
        val resultPath: String
        val timestamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val file = createImageFile("Foto${timestamp}").apply {
            currentPhotoPath = absolutePath

            resultPath = absolutePath

        }
        val photoURI = FileProvider.getUriForFile(requireActivity(), authority, file)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
            putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        }
        resultLauncher.launch(intent)
        return resultPath
    }

    private fun createImageFile(name: String) =
        File.createTempFile(
            name,
            ".jpg",
            activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        )

}