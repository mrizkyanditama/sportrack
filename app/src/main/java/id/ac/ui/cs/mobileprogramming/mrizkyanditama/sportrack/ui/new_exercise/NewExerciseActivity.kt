package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.new_exercise

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.observe
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.DataBindingActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.NewExerciseBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.SportTypeAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.start_exercise.StartExerciseActivity

@AndroidEntryPoint
class NewExerciseActivity : DataBindingActivity() {

    @VisibleForTesting val viewModel: NewExerciseViewModel by viewModels()
    private val binding: NewExerciseBinding by binding(R.layout.new_exercise)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sportTypeAdapter = SportTypeAdapter(SportTypeAdapter.SportTypeListener { sportType -> viewModel.onSportTypeItemClick(sportType) })
        binding.apply {
            lifecycleOwner = this@NewExerciseActivity
            adapter = sportTypeAdapter
            vm = viewModel
        }
        val continueObserver = Observer<Boolean> {
            if (it){
                val intent = Intent(applicationContext, StartExerciseActivity::class.java)
                intent.putExtra("SPORT_TYPE", viewModel.selectedSportType.value)
                this.startActivity(intent)
            }
        }
        viewModel.isContinue.observe(this, continueObserver)
    }

}