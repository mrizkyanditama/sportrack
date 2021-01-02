/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.news

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.DataBindingActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.NewsBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.NewsArticle
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.NewsAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.camera.WorkoutPhotoActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.news_detail.NewsDetailActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.start_exercise.StartExerciseActivity

@AndroidEntryPoint
class NewsActivity : DataBindingActivity() {

  @VisibleForTesting val viewModel: NewsViewModel by viewModels()
  private val binding: NewsBinding by binding(R.layout.news)

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    if(isOnline(context = applicationContext)){
      renderListNews()
    }else{
      setContentView(R.layout.empty_layout)
      findViewById<Button>(R.id.button_refresh).setOnClickListener {
        if(isOnline(applicationContext)){
          finish();
          startActivity(getIntent());
        }
      }
    }
    val navigation = findViewById<View>(R.id.bottom_navigation) as BottomNavigationView
    navigation.setOnNavigationItemSelectedListener { item ->
      when (item.getItemId()) {
        R.id.page_1 -> {
          val b = Intent(this@NewsActivity, HomeActivity::class.java)
          startActivity(b)
        }
        R.id.page_3 -> {
          val b = Intent(this@NewsActivity, WorkoutPhotoActivity::class.java)
          startActivity(b)
        }
      }
      false
    }
  }



  fun renderListNews() {
    binding.apply {
      lifecycleOwner = this@NewsActivity
      adapter = NewsAdapter(NewsAdapter.NewsListener { news -> viewModel.onNewsClick(news)})
      vm = viewModel
    }
    val newsObserver = Observer<NewsArticle> {
      if (it != null){
        val intent = Intent(applicationContext, NewsDetailActivity::class.java)
        intent.putExtra("URL_NEWS", viewModel.selectedNews.value?.url)
        this.startActivity(intent)
      }
    }
    viewModel.selectedNews.observe(this, newsObserver)
  }

  fun isOnline(context: Context): Boolean {
    val connectivityManager =
      context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (connectivityManager != null) {
      val capabilities =
        connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
      if (capabilities != null) {
        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
          return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
          return true
        } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
          return true
        }
      }
    }
    return false
  }

}
