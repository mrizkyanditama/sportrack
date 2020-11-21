package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.ItemExerciseBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.Exercise
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.ExerciseAndSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.ExerciseSportType

class ExerciseAdapter: RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder>() {

    private val items: MutableList<ExerciseSportType> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExerciseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemExerciseBinding>(inflater, R.layout.item_exercise, parent, false)
        return ExerciseAdapter.ExerciseViewHolder(binding)
    }

    fun addExerciseList(exerciseList: List<ExerciseSportType>){
        val previous = items.size
        items.addAll(exerciseList)
        notifyItemRangeChanged(previous, exerciseList.size)
    }

    override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
        holder.binding.apply {
            exercise = items[position]
            executePendingBindings()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ExerciseViewHolder(val binding: ItemExerciseBinding):
        RecyclerView.ViewHolder(binding.root)
}