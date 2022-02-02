package com.rrohaill.refactoringsample.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rrohaill.refactoringsample.data.Place
import com.rrohaill.refactoringsample.databinding.RecyclerItemBinding

class PlacesAdapter(private var places: List<Place>) : RecyclerView.Adapter<PlacesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerItemBinding.inflate(inflater, parent, false)

        return PlacesViewHolder(binding)
    }

    override fun getItemCount(): Int = places.size

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bindData(places[position])
    }
}
