package com.example.inputfabric

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FabricFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var fabricList: ArrayList<Fabric>
    private lateinit var fabricAdapter: FabricAdapter
    private lateinit var inputId: EditText
    private lateinit var inputType: EditText
    private lateinit var inputColor: EditText
    private lateinit var inputQty: EditText
    private lateinit var buttonSubmit: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fabric, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        fabricList = arrayListOf()
        fabricAdapter = FabricAdapter(fabricList)
        recyclerView.adapter = fabricAdapter

        inputId = view.findViewById(R.id.inputId)
        inputType = view.findViewById(R.id.inputType)
        inputColor = view.findViewById(R.id.inputColor)
        inputQty = view.findViewById(R.id.inputQty)
        buttonSubmit = view.findViewById(R.id.buttonSubmit)

        val database = FirebaseDatabase.getInstance().reference.child("fabrics")

        buttonSubmit.setOnClickListener {
            val id = inputId.text.toString()
            val type = inputType.text.toString()
            val color = inputColor.text.toString()
            val qty = inputQty.text.toString().toInt()
            val fabric = Fabric(id, type, color, qty)
            database.child(id).setValue(fabric)
        }

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                fabricList.clear()
                for (dataSnapshot in snapshot.children) {
                    val fabric = dataSnapshot.getValue(Fabric::class.java)
                    if (fabric != null) {
                        fabricList.add(fabric)
                    }
                }
                fabricAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle possible errors.
            }
        })

        return view
    }
}
