package com.pablogarcia.my_movies.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pablogarcia.my_movies.model.Movie
import com.pablogarcia.my_movies.R

class MoviesAdapter(
    var onLikeButtonClicked: ((Movie) -> Unit)? = null
): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    private var movies: List<Movie> = listOf()
    private var likedMovies: List<Movie> = listOf()

    //region OVERRIDE_METHODS

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
            R.layout.row_movie,
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    //endregion

    //region PUBLIC_METHODS

    /**
     * Set new movies data
     *
     * @param movies - new movies list
     */
    fun updateMovies(movies: List<Movie>){
        this.movies = movies
        updateLikedMoviesValue()
        notifyDataSetChanged()
    }

    /**
     * Set new liked movies data
     *
     * @param likedMovies - new liked movies list
     */
    fun updateLikedMovies(likedMovies: List<Movie>) {
        this.likedMovies = likedMovies
        updateLikedMoviesValue()
        notifyDataSetChanged()
    }

    /**
     * Update movie with new liked value
     *
     * @param movie - movie to update
     * @param position - movie array position
     */
    fun updateLikedMovie(movie: Movie, position: Int) {
        movie.liked = !movie.liked
        notifyItemChanged(position)
        onLikeButtonClicked?.invoke(movie)
    }

    //endregion

    //region PRIVATE_METHODS

    /**
     * Update movies liked value with liked movies list
     */
    private fun updateLikedMoviesValue() {
        likedMovies.forEach { movie ->
            this.movies.find { it.id == movie.id }?.liked = movie.liked
        }
    }
    //endregion

    /**
     * Movie Horizontal view holder
     */
    inner class MovieViewHolder(private var view: View): RecyclerView.ViewHolder(view){

        private var text : AppCompatTextView = view.findViewById(R.id.movie_id)
        private var likeIcon : AppCompatImageView = view.findViewById(R.id.like_button)
        fun bind(movie: Movie) {
            text.text = "Pelicula con ID: $movie"
            likeIcon.setOnClickListener { updateLikedMovie(movie, adapterPosition) }
            val drawable = if (movie.liked) {
                ContextCompat.getDrawable(view.context, R.drawable.ic_like_selected)
            } else {
                ContextCompat.getDrawable(view.context, R.drawable.ic_like)
            }
            likeIcon.setImageDrawable(drawable)
        }
    }
}
