package com.joshhn.trelloclone.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.joshhn.trelloclone.R
import com.joshhn.trelloclone.databinding.ActivityMyProfileBinding
import com.joshhn.trelloclone.firebase.FirestoreClass
import com.joshhn.trelloclone.models.User
import java.io.IOException

class MyProfileActivity :BaseActivity() {

    companion object{
        private const val READ_STORAGE_PERMISSION_CODE = 1
        private const val PICK_IMAGE_REQUEST_CODE = 2
    }

    private var mSelectedImageFileUri: Uri? = null

    private var binding: ActivityMyProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfileBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupActionBar()

        FirestoreClass().loadUserData(this)

        binding?.ivProfileUserImage?.setOnClickListener {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

            }else{
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    READ_STORAGE_PERMISSION_CODE
                )
            }
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == READ_STORAGE_PERMISSION_CODE){
            if(grantResults.isNotEmpty()
                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                showImageChooser()
            }
        }else{
            Toast.makeText(
                this,
                "You just denied the permission for storage. You can also allow it from settings.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun showImageChooser() {
        var galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK
            && requestCode == PICK_IMAGE_REQUEST_CODE
            && data!!.data != null){
            mSelectedImageFileUri = data.data

            try {
                Glide
                    .with(this)
                    .load(mSelectedImageFileUri)
                    .centerCrop()
                    .placeholder(R.drawable.ic_user_place_holder)
                    .into(binding?.ivProfileUserImage!!)
            }catch (e: IOException){
                e.printStackTrace()
        }
        }
    }


    private fun setupActionBar(){
        setSupportActionBar(binding?.toolbarMyProfileActivity)

        val actionBar = supportActionBar
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
            actionBar.title = resources.getString(R.string.my_profile)
        }

        binding?.toolbarMyProfileActivity?.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    fun setUserDataInUI(user: User){

        val userImage = binding?.ivProfileUserImage
        val userName = binding?.etName
        val userEmail = binding?.etEmail
        val userMobile = binding?.etMobile

        if (userImage != null) {
            Glide
                .with(this)
                .load(user.image)
                .centerCrop()
                .placeholder(R.drawable.ic_user_place_holder)
                .into(userImage)
        }
        userName?.setText(user.name)
        userEmail?.setText(user.email)
        if(user?.mobile != 0L){
            userMobile?.setText(user.mobile.toString())
        }


    }
}