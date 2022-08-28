package com.example.cinnabonapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cinnabonapp.R
import com.example.cinnabonapp.model.Cinnabon
import com.example.cinnabonapp.viewmodel.CinnabonViewModel
import com.google.android.material.card.MaterialCardView

class CinnabonAdapter(private val sharedViewModel: CinnabonViewModel)
    : RecyclerView.Adapter<CinnabonAdapter.CinnabonViewHolder>() {

    private val dataSet: List<Cinnabon> = listOf(
        Cinnabon(R.drawable.classic_roll,R.string.classic_roll),
        Cinnabon(R.drawable.caramel_pecanbon,R.string.caramel_pecanbon),
        Cinnabon(R.drawable.center_of_the_roll,R.string.center_of_the_roll),
        Cinnabon(R.drawable.cinna_sweetie,R.string.cinna_sweeties),
        Cinnabon(R.drawable.cinnabon_stix,R.string.cinnabon_stix),
        Cinnabon(R.drawable.cookie_bonbite,R.string.cookie_bonbite),
    )
    class CinnabonViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imageView:ImageView = view.findViewById(R.id.imageView)
        val textView:TextView = view.findViewById(R.id.textView)
        val cardView:MaterialCardView = view.findViewById(R.id.card_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinnabonViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item,parent,false)

        return CinnabonViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: CinnabonViewHolder, position: Int) {
        val item = dataSet[position]

        holder.imageView.setImageResource(item.cinnabonImageRes)
        holder.textView.setText(item.cinnabonNameId)
        val context = holder.itemView
        val hold = holder.textView
        holder.cardView.setOnClickListener {
            sharedViewModel.setCinnabonType(hold.text.toString())
            Log.d("TAG", "onBindViewHolder: ${sharedViewModel.cinnabonType} ")
            context.findNavController().navigate(R.id.action_cinnabonListFragment_to_cinnabonNumberFragment)
        }
    }

    override fun getItemCount() = dataSet.size
}