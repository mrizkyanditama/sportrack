package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.github.florent37.glidepalette.BitmapPalette
import com.github.florent37.glidepalette.GlidePalette
import com.google.android.material.card.MaterialCardView
import com.skydoves.whatif.whatIfNotNullOrEmpty
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.SportTypeAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.new_exercise.NewExerciseViewModel

@BindingAdapter("adapterSportType")
fun bindAdapterSportType(view: RecyclerView, adapter: RecyclerView.Adapter<SportTypeAdapter.SportTypeViewHolder>) {
    view.adapter = adapter
}

@BindingAdapter("adapterSportTypeList")
fun bindAdapterSportTypeList(view: RecyclerView, sportTypeList: List<SportType>?) {
    sportTypeList.whatIfNotNullOrEmpty {
        (view.adapter as? SportTypeAdapter)?.addSportTypeList(it)
    }
}

@BindingAdapter("sportTypeImage")
fun bindLoadImagePalette(view: AppCompatImageView, url: String) {
    Glide.with(view.context)
        .load(url)
        .into(view)
}

//@BindingAdapter("sportTypeItem", "onItemSportTypeClick")
//fun bindItemSportType(view: MaterialCardView, sportTypeItem: SportType, viewModel: NewExerciseViewModel) {
//    if (viewModel.selectedSportType.value === sportTypeItem){
//        viewModel.selectedSportType.postValue(null)
//    }
//    else{
//        viewModel.selectedSportType.postValue(sportTypeItem)
//        viewModel.computedCaloriesLiveData.postValue("${sportTypeItem.caloriesBurnedPerMinute} kalori per menit")
//    }
//}
