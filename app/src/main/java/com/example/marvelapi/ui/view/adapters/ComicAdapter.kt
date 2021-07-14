package com.example.marvelapi.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapi.data.model.Result
import com.example.marvelapi.databinding.ComicItemBinding
import com.example.marvelapi.utils.loadImage

class ComicAdapter(
    private val comicClickListener: (result: Result) -> Unit
) : RecyclerView.Adapter<ComicAdapter.ComicViewHolder>() {
    private val comicsList = mutableListOf<Result>()

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ) = ComicViewHolder(
        ComicItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    ).also { holder ->
        holder.itemView.setOnClickListener {
            comicClickListener.invoke(comicsList[holder.adapterPosition])
        }
    }


    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        holder.bind(comicsList[position])
    }

    override fun getItemCount() = comicsList.size

    fun updateList(comics: List<Result>) {
        comicsList.clear()
        comicsList.addAll(comics)
        notifyDataSetChanged()
    }

    class ComicViewHolder(
        private val binding: ComicItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) = with(binding) {
            thumbnailIv.loadImage(result.thumbnail.path)
            titleTv.text = result.title
        }
    }
}