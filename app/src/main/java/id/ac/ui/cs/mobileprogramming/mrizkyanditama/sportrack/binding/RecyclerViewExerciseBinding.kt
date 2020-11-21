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

package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.skydoves.baserecyclerviewadapter.RecyclerViewPaginator
import com.skydoves.whatif.whatIfNotNullOrEmpty
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.ExerciseSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.ExerciseAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeViewModel

@BindingAdapter("adapterExercise")
fun bindAdapterExerciseList(view: RecyclerView, adapter: RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>) {
    view.adapter = adapter
}

@BindingAdapter("paginationExerciseList")
fun paginationExerciseList(view: RecyclerView, viewModel: HomeViewModel) {
    RecyclerViewPaginator(
        recyclerView = view,
        isLoading = { viewModel.isLoading.get() },
        loadMore = { viewModel.fetchExerciseList(it) },
        onLast = { false }
    ).run {
        threshold = 8
    }
}

@BindingAdapter("adapterExerciseList")
fun bindAdapterExerciseList(view: RecyclerView, exerciseList: List<ExerciseSportType>?) {
    exerciseList.whatIfNotNullOrEmpty {
        (view.adapter as? ExerciseAdapter)?.addExerciseList(it)
    }
}
