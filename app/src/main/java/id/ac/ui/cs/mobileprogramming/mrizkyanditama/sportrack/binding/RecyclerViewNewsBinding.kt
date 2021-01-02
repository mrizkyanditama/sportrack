package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.whatif.whatIfNotNullOrEmpty
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.NewsArticle
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.ExerciseSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.ExerciseAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.NewsAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeViewModel

@BindingAdapter("adapterNews")
fun bindAdapterExerciseList(view: RecyclerView, adapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>) {
    view.adapter = adapter
}

@BindingAdapter("adapterNewsList")
fun bindAdapterExerciseList(view: RecyclerView, newsArticleList: List<NewsArticle>?) {
    newsArticleList.whatIfNotNullOrEmpty {
        (view.adapter as? NewsAdapter)?.addNewsList(it)
    }
}
