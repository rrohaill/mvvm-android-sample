package com.rrohaill.refactoringsample.view

import androidx.recyclerview.widget.RecyclerView
import com.rrohaill.refactoringsample.data.Place
import com.rrohaill.refactoringsample.databinding.RecyclerItemBinding

class PlacesViewHolder(private val binding: RecyclerItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindData(place: Place) {
        binding.apply {
            data = place
        }
    }
}