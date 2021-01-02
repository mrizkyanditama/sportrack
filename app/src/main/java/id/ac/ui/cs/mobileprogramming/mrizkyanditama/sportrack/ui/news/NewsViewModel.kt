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

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.LiveCoroutinesViewModel
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.NewsArticle
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository.NewsRepository
import timber.log.Timber

class NewsViewModel @ViewModelInject constructor(
  private val newsRepository: NewsRepository,
  @Assisted private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

  private var pokemonFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
  val newsListLiveData: LiveData<List<NewsArticle>>

  private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
  val toastLiveData: LiveData<String> get() = _toastLiveData

  val isLoading: ObservableBoolean = ObservableBoolean(false)

  val selectedNews: MutableLiveData<NewsArticle> = MutableLiveData()

  init {
    Timber.d("init MainViewModel")
      newsListLiveData = launchOnViewModelScope {
        this.newsRepository.fetchNewsList(
          onSuccess = { isLoading.set(false) },
          onError = { _toastLiveData.postValue(it) }
        ).asLiveData()
    }
  }

  fun onNewsClick(newsArticle: NewsArticle) {
    selectedNews.postValue(newsArticle)
  }

}
