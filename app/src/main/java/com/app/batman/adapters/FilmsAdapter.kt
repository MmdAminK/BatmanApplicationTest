package com.app.batman.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.batman.R
import com.app.batman.databinding.FilmCardViewBinding
import com.app.batman.models.Film

class FilmsAdapter : RecyclerView.Adapter<FilmsAdapter.MainPageViewHolder>() {

    private var films: List<Film> = emptyList()
    private lateinit var filmCardViewBinding: FilmCardViewBinding
    private lateinit var onClick: (position: Int) -> Unit

    fun setFilms(films: List<Film>) {
        this.films = films
    }

    fun setOnItem(onClick: (position: Int) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainPageViewHolder {
        filmCardViewBinding =
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.film_card_view, parent, false
            )
        return MainPageViewHolder(filmCardViewBinding, onClick)
    }

    override fun onBindViewHolder(holder: MainPageViewHolder, position: Int) {
        val film = films[position]
        holder.bind(film)
    }

    class MainPageViewHolder(
        private val filmCardViewBinding: FilmCardViewBinding,
        private val onClick: (position: Int) -> Unit
    ) : RecyclerView.ViewHolder(filmCardViewBinding.root) {
        fun bind(film: Film) {
            filmCardViewBinding.film = film
            filmCardViewBinding.executePendingBindings()
        }

        init {
            filmCardViewBinding.root.setOnClickListener {
                val position: Int = layoutPosition
                if (position != RecyclerView.NO_POSITION) onClick(position)
            }
        }
    }

    override fun getItemCount(): Int {
        return films.size
    }

}





