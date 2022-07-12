package com.joshhn.trelloclone.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.bumptech.glide.Glide
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.joshhn.trelloclone.R
import com.joshhn.trelloclone.databinding.ActivityMainBinding
import com.joshhn.trelloclone.databinding.NavHeaderMainBinding
import com.joshhn.trelloclone.firebase.FirestoreClass
import com.joshhn.trelloclone.models.User

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var  binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setupActionBar()

        binding?.navView?.setNavigationItemSelectedListener(this)

        FirestoreClass().signInUser(this)

    }

    private fun setupActionBar() {

        setSupportActionBar(binding?.include?.toolbarMainActivity)
        binding?.include?.toolbarMainActivity?.setNavigationIcon(R.drawable.ic_action_navigation_menu)

        binding?.include?.toolbarMainActivity?.setNavigationOnClickListener {
            toggleDrawer()
        }
    }

    private fun toggleDrawer() {

        if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            binding?.drawerLayout?.openDrawer(GravityCompat.START)
        }
    }

    override fun onBackPressed() {
        if (binding?.drawerLayout?.isDrawerOpen(GravityCompat.START) == true) {
            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        } else {
            doubleBackToExit()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_my_profile -> {

                startActivityForResult(
                    Intent(this@MainActivity, MyProfileActivity::class.java),
                    MY_PROFILE_REQUEST_CODE
                )
            }

            R.id.nav_sign_out -> {
                // Here sign outs the user from firebase in this device.
                FirebaseAuth.getInstance().signOut()

                mSharedPreferences.edit().clear().apply()

                // Send the user to the intro screen of the application.
                val intent = Intent(this, IntroActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        binding?.drawerLayout?.closeDrawer(GravityCompat.START)
        return true
    }

    fun updateNavigationUserDetails(user: User){
        val navHeaderMainBinding = NavHeaderMainBinding.inflate(layoutInflater)
        val userImage = navHeaderMainBinding?.ivUserImage
        val userName = navHeaderMainBinding?.tvUsername

        if (userImage != null) {
            Glide
                .with(this)
                .load(user.image)
                .centerCrop()
                .placeholder(R.drawable.ic_user_place_holder)
                .into(userImage)
        }

        userName?.text = user.name

    }

}