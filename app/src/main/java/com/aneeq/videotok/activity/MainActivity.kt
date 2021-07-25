package com.aneeq.videotok.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast

import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.iterator
import androidx.drawerlayout.widget.DrawerLayout
import com.aneeq.videotok.R
import com.aneeq.videotok.fragments.DiscoverFragment
import com.aneeq.videotok.fragments.HomeFragment
import com.aneeq.videotok.fragments.InboxFragment
import com.aneeq.videotok.fragments.ProfileFragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var bnv: BottomNavigationView
    lateinit var frame: FrameLayout
    lateinit var drawerLayout: DrawerLayout
    lateinit var coordinatorLayout: CoordinatorLayout
    lateinit var collapsingtoolbar: CollapsingToolbarLayout
    lateinit var toolbar: Toolbar
    var previousMenuItem: MenuItem? = null

    companion object {
        val IMAGE_PICK = 1000
        val REQUEST_CODE = 1234
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        coordinatorLayout = findViewById(R.id.coordinatorLayout)
        toolbar = findViewById(R.id.toolbar)
        collapsingtoolbar = findViewById(R.id.collapsingtoolbar)
        bnv = findViewById(R.id.bnv)
        frame = findViewById(R.id.frame)
        verifyPermissions()
        openHome()
    }

    private fun setUpBNV() {
        bnv.setOnNavigationItemSelectedListener { item ->


            when (item.itemId) {
                R.id.home -> {
                    openHome()
                }
                R.id.search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            DiscoverFragment()
                        ).commit()
                }
                R.id.camera -> {
                    openCamera()
                }
                R.id.inbox -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            InboxFragment()
                        ).commit()
                }
                R.id.account -> {
                    supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.frame,
                            ProfileFragment()
                        ).commit()
                }
            }
            return@setOnNavigationItemSelectedListener true
        }

    }

    private fun openHome() {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.frame,
                HomeFragment()
            ).commit()

    }

    override fun onBackPressed() {
        val selectedItemId = bnv.selectedItemId
        if (R.id.home != selectedItemId) {
            openHome()
            bnv.menu.getItem(0).isChecked = true
        } else
            super.onBackPressed()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        verifyPermissions()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 1)
    }

    private fun verifyPermissions() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    permissions[0]
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    permissions[1]
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    permissions[2]
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                setUpBNV()
            } else {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
            }
        }

    }
}