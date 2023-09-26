package com.kevin.shoppingapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.kevin.shoppingapp.Adapter.ItemAdapter
import com.kevin.shoppingapp.Model.ShoppingModel
import com.kevin.shoppingapp.databinding.FragmentItemShowBinding

class ItemShowFragment : Fragment() {

    lateinit var binding : FragmentItemShowBinding
    var adapter = ItemAdapter()
    var shoppinglist = ArrayList<ShoppingModel>()
    lateinit var dbRef: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentItemShowBinding.inflate(layoutInflater)

        getList()

        binding.rcvshopping.layoutManager = GridLayoutManager(context, 2)
        binding.rcvshopping.adapter = adapter
        adapter.update(shoppinglist)

        return binding.root
    }

    private fun getList() {

        dbRef = FirebaseDatabase.getInstance().getReference("Shopping")

        dbRef.root.child("Shopping").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                shoppinglist.clear()
                for (snap in snapshot.children) {
                    var model = snap.getValue(ShoppingModel::class.java)
                    shoppinglist.add(model!!)
                }
                adapter.update(shoppinglist)
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    }