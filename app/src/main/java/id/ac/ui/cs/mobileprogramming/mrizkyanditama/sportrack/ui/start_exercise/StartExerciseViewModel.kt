package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.start_exercise

import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.*
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.LiveCoroutinesViewModel
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.Exercise
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository.ExerciseRepository
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.stopwatch.StopwatchService
import kotlinx.coroutines.coroutineScope
import timber.log.Timber
import java.util.*

class StartExerciseViewModel @AssistedInject constructor(
    private val exerciseRepository: ExerciseRepository,
    @Assisted private val sportType: SportType
) : LiveCoroutinesViewModel() {

    private var sportTypeExercise: SportType
    private var isStarted: Boolean = false
    var stopwatchTimeLiveData: MutableLiveData<String> = MutableLiveData("00:00:000")
    var buttonTextLiveData: MutableLiveData<String> = MutableLiveData("Mulai Olahraga")

    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var isDone: LiveData<Boolean> = MutableLiveData(false)
    var isFinished: MutableLiveData<Boolean> = MutableLiveData(false)


    var handler = Handler()

    internal var MillisecondTime: Long = 0
    internal var StartTime: Long = 0
    internal var TimeBuff: Long = 0
    internal var UpdateTime = 0L


    internal var Seconds: Int = 0
    internal var Minutes: Int = 0
    internal var MilliSeconds: Int = 0


    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(sportType: SportType): StartExerciseViewModel
    }

    init {
        Timber.d("init StartExerciseViewModel")
        sportTypeExercise = sportType
    }

    var runnable: Runnable = object : Runnable {

        override fun run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime

            UpdateTime = TimeBuff + MillisecondTime

            Seconds = (UpdateTime / 1000).toInt()

            Minutes = Seconds / 60

            Seconds = Seconds % 60

            MilliSeconds = (UpdateTime % 1000).toInt()

            var text = ""
            if (Minutes.toString().length < 2) {
                text += "0" + Minutes.toString()
            } else {
                text += Minutes.toString()
            }
            text += ":"
            if (Seconds.toString().length < 2) {
                text += "0" + Seconds.toString()
            } else {
                text += Seconds.toString()
            }
            text += ":"
            text += MilliSeconds.toString()
            stopwatchTimeLiveData.postValue(text)
            handler?.postDelayed(this, 10)
        }

    }


    fun startStopwatch(){
        isStarted = true
        Timber.d((Looper.myLooper() == Looper.getMainLooper()).toString())
        buttonTextLiveData.postValue("Stop")
        StartTime = SystemClock.uptimeMillis()
        handler?.postDelayed(runnable, 0)
    }

    fun stopStopwatch(){
        isStarted = false
        handler?.removeCallbacks(runnable)
    }

    fun onButtonClick(){
        if(!isStarted){
            startStopwatch()
        }else{
            stopStopwatch()
            val minutesExercise = Minutes
            this.exerciseRepository.insertExercise(Exercise(
                exerciseId = null,
                dateOfExercise = Calendar.getInstance().time,
                caloriesBurned = minutesExercise * sportTypeExercise.caloriesBurnedPerMinute,
                sportTypeDoneId = sportTypeExercise.sportTypeId,
                minutesExercise = minutesExercise),onSuccess = {isFinished.postValue(true)})
//            isDone = launchOnViewModelScope {
//                exerciseRepository.insertExercise(Exercise(
//                    exerciseId = null,
//                    dateOfExercise = Calendar.getInstance().time,
//                    caloriesBurned = minutesExercise * sportTypeExercise.caloriesBurnedPerMinute,
//                    sportTypeDoneId = sportTypeExercise.sportTypeId,
//                    minutesExercise = minutesExercise
//                ), onSuccess = {isFinished.postValue(true)}).asLiveData()
//            }
        }
    }

    companion object{
        fun provideFactory(
            assistedFactory: AssistedFactory,
            sportType: SportType
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(sportType) as T
            }
        }
    }

}