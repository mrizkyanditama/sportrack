package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.LiveCoroutinesViewModel
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.Exercise
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.ExerciseAndSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.ExerciseSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository.ExerciseRepository
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository.SportTypeRepository
import timber.log.Timber

class HomeViewModel @ViewModelInject constructor(
    private val exerciseRepository: ExerciseRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

    private var exerciseFetchingLiveData: MutableLiveData<Int> = MutableLiveData()
    val exerciseListLiveData: LiveData<List<ExerciseSportType>>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    val isContinue: MutableLiveData<Boolean> = MutableLiveData(false)


    init {
        Timber.d("init HomeViewModel")
        exerciseListLiveData =
            launchOnViewModelScope {
                this.exerciseRepository.fetchExerciseSportTypeList(
                    onSuccess = { isLoading.set(false) }
                ).asLiveData()
            }
    }

    fun onClickFloatingButton() {
        isContinue.postValue(true)
    }

    @MainThread
    fun fetchExerciseList(page: Int) {
        exerciseFetchingLiveData.value = page
    }

}