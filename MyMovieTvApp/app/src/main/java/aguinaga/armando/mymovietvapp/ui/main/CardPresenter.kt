package aguinaga.armando.mymovietvapp.ui.main

import aguinaga.armando.mymovietvapp.data.model.Movie
import aguinaga.armando.mymovietvapp.di.NetworkModule
import aguinaga.armando.mymovietvapp.ui.common.loadUrl
import android.view.ViewGroup
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter
import timber.log.Timber


class CardPresenter: Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val carView = ImageCardView(parent.context).apply {
            isFocusable = true
            isFocusableInTouchMode = true
            setMainImageDimensions(176,313)
        }
        return ViewHolder(carView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val movie = item as Movie
        with(viewHolder.view as ImageCardView) {
            titleText = movie.title
            contentText = movie.releaseDate
            //Timber.e("${NetworkModule.BASE_IMAGES}${movie.poster}")
            mainImageView.loadUrl("${NetworkModule.BASE_IMAGES}${movie.poster}")
            //mainImageView.loadUrl("${movie.poster}")
        }

    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        with(viewHolder.view as ImageCardView) {
            mainImage = null
        }
    }
}