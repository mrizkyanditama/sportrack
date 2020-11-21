package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.start_exercise

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.DataBindingActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.StartExerciseBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.extensions.argument
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeActivity
import javax.inject.Inject

@AndroidEntryPoint
class StartExerciseActivity : DataBindingActivity() {
    @Inject
    lateinit var startExerciseViewModelFactory: StartExerciseViewModel.AssistedFactory
    private val binding: StartExerciseBinding by binding(R.layout.start_exercise)
    private val sportTypeItem: SportType by argument("SPORT_TYPE")

    @VisibleForTesting
    val viewModel: StartExerciseViewModel by viewModels {
        StartExerciseViewModel.provideFactory(startExerciseViewModelFactory, sportTypeItem)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@StartExerciseActivity
            sporttype = sportTypeItem
            vm = viewModel
        }
        val doneObserver = Observer<Boolean> {
            if (it == true){
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                finish()
                startActivity(intent)
            }
        }
        viewModel.isFinished.observe(this, doneObserver)
    }
}