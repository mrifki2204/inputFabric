package com.example.inputfabric
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FabricAdapter(private val fabricList: List<Fabric>) : RecyclerView.Adapter<FabricAdapter.FabricViewHolder>() {

    class FabricViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idTextView: TextView = itemView.findViewById(R.id.idTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
        val colorTextView: TextView = itemView.findViewById(R.id.colorTextView)
        val qtyTextView: TextView = itemView.findViewById(R.id.qtyTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FabricViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fabric_item, parent, false)
        return FabricViewHolder(view)
    }
    override fun onBindViewHolder(holder: FabricViewHolder, position: Int) {
        val fabric = fabricList[position]
        holder.idTextView.text = fabric.id
        holder.typeTextView.text = fabric.type
        holder.colorTextView.text = fabric.color
        holder.qtyTextView.text = fabric.qty.toString()
    }

    override fun getItemCount() = fabricList.size
}

