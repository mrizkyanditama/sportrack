package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter

import android.content.Intent
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.RowNewsArticleBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.NewsArticle
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.news.NewsActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.news_detail.NewsDetailActivity

class NewsAdapter(clickListener: NewsListener) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val items: MutableList<NewsArticle> = mutableListOf()
    private var onClickedAt = 0L
    private val clickListener = clickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<RowNewsArticleBinding>(
                inflater,
                R.layout.row_news_article,
                parent,
                false
            )
        return NewsViewHolder(binding)
    }

    fun addNewsList(pokemonList: List<NewsArticle>) {
        val previous = items.size
        items.addAll(pokemonList)
        notifyItemRangeChanged(previous, pokemonList.size)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.apply {
            news = items[position]
            listener = clickListener
            executePendingBindings()
        }
    }

    override fun getItemCount() = items.size

    class NewsViewHolder(val binding: RowNewsArticleBinding) :
        RecyclerView.ViewHolder(binding.root)

    class NewsListener(var clickListener: (newsArticle: NewsArticle) -> Unit) {
        fun onClick(newsArticle: NewsArticle) = clickListener(newsArticle)
    }
}
