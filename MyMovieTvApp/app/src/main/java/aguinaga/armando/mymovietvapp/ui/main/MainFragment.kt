package aguinaga.armando.mymovietvapp.ui.main

import aguinaga.armando.mymovietvapp.R
import aguinaga.armando.mymovietvapp.data.model.Movie
import aguinaga.armando.mymovietvapp.data.model.toDomainMovie
import aguinaga.armando.mymovietvapp.di.NetworkModule
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.*
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainFragment: BrowseSupportFragment() {

    private val moviesViewmodel: MoviesViewmodel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = getString(R.string.browse)
        Timber.e("${NetworkModule.BASE}")
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        moviesViewmodel.obtenerMovies(true,getString(R.string.api_key))
        moviesViewmodel.getMovies.observe(viewLifecycleOwner) { re ->

            val listRowAdapter = ArrayObjectAdapter(CardPresenter())
            val movies = re?.results?.map { it.toDomainMovie() }
            (1..5).forEach { categoryId ->
                val categoryTitle = "Category $categoryId"

                listRowAdapter.addAll(0, movies)
                val header = HeaderItem(categoryId.toLong(), categoryTitle)
                rowsAdapter.add(ListRow(header, listRowAdapter))

            }
        }
        adapter = rowsAdapter
    }
}
