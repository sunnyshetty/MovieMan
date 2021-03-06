package com.calvinnor.movie.discover.ui

import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.calvinnor.core.extensions.*
import com.calvinnor.core.pagination.BottomPaginationAdapter
import com.calvinnor.core.pagination.PaginationListener
import com.calvinnor.core.pagination.PaginationViewHolder
import com.calvinnor.movie.R
import com.calvinnor.movie.commons.model.MovieUiModel
import com.calvinnor.movie.details.ui.MovieDetailsFragmentArgs
import kotlinx.android.synthetic.main.item_movie.view.*

class DiscoverMoviesAdapter(listener: PaginationListener) :
    BottomPaginationAdapter<MovieUiModel>(listener) {

    override fun onCreateVH(parent: ViewGroup, viewType: Int) = MovieViewHolder(
        parent.inflate(R.layout.item_movie)
    )

    override fun onBindVH(holder: PaginationViewHolder, position: Int) {
        if (holder is MovieViewHolder) holder.bind(dataItems[position])
    }

    override fun areItemsSame(oldItem: MovieUiModel, newItem: MovieUiModel) =
        oldItem.id == newItem.id

    class MovieViewHolder(rootView: View) : PaginationViewHolder(rootView) {

        fun bind(uiModel: MovieUiModel) = with(itemView) {
            ivBackdrop.setImage(
                uiModel.backdropImage,
                scaleType = ScaleType.CENTER_CROP,
                onFailure = { ivBackdrop.defaultImage() },
                fadeIn = false
            )

            tvTitle.text = uiModel.title
            tvRelease.setTextOrGone(uiModel.releaseDate)

            clRoot.setOnClickListener {

                findNavController().navigate(
                    R.id.navigateFromHomeToMovieDetails,
                    MovieDetailsFragmentArgs(movieId = uiModel.id).toBundle()
                )
            }
        }
    }
}