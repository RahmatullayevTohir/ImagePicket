package com.example.imagepicket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private lateinit var selectedImage : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

    }

    private fun initViews() {
        val imagePicket = findViewById<Button>(R.id.btn_image_picket)
        selectedImage =findViewById(R.id.iv_image)
        imagePicket.setOnClickListener {
            pickImage()
        }
    }



    private fun pickImage(){
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        intent.type  ="image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        launcher.launch(intent)
    }

    val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val image  = it.data!!.data
            selectedImage.setImageURI(image)
        }
    }

}