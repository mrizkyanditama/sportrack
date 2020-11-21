package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.Observer
import com.skydoves.transformationlayout.onTransformationStartContainer
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.DataBindingActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.HomeBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.adapter.ExerciseAdapter
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.new_exercise.NewExerciseActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.start_exercise.StartExerciseActivity

@AndroidEntryPoint
class HomeActivity : DataBindingActivity() {
    @VisibleForTesting val viewModel: HomeViewModel by viewModels()
    private val binding: HomeBinding by binding(R.layout.home)

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@HomeActivity
            adapter = ExerciseAdapter()
            vm = viewModel.apply { fetchExerciseList(0) }
        }
        val continueObserver = Observer<Boolean> {
            if (it){
                val intent = Intent(this, NewExerciseActivity::class.java)
                this.startActivity(intent)
            }
        }
        viewModel.isContinue.observe(this, continueObserver)
    }
}