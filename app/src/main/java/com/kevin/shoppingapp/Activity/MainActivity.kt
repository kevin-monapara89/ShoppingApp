package com.kevin.shoppingapp.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.kevin.shoppingapp.Fragment.AddItemFragment
import com.kevin.shoppingapp.Fragment.ItemShowFragment
import com.kevin.shoppingapp.R
import com.kevin.shoppingapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(ItemShowFragment())
        binding.bottom.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> replaceFragment(ItemShowFragment())
                R.id.add -> replaceFragment(AddItemFragment())

                else -> {

                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragpageview, fragment).commit()
    }
}