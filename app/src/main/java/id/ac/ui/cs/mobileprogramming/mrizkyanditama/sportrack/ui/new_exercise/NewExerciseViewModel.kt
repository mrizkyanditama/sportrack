package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.new_exercise

import androidx.annotation.MainThread
import androidx.databinding.ObservableBoolean
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.asLiveData
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.LiveCoroutinesViewModel
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository.SportTypeRepository
import timber.log.Timber

class NewExerciseViewModel @ViewModelInject constructor(
    private val sportTypeRepository: SportTypeRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : LiveCoroutinesViewModel() {

    val sportTypeListLiveData: LiveData<List<SportType>>

    private val _toastLiveData: MutableLiveData<String> = MutableLiveData()
    val toastLiveData: LiveData<String> get() = _toastLiveData

    val isLoading: ObservableBoolean = ObservableBoolean(false)

    val isContinue: MutableLiveData<Boolean> = MutableLiveData(false)

    val selectedSportType: MutableLiveData<SportType> = MutableLiveData()

    val computedCaloriesLiveData: MutableLiveData<String> = MutableLiveData()

    fun onSportTypeItemClick(sportType: SportType) {
        Timber.d(sportType.name)
        if (selectedSportType.value === sportType){
            selectedSportType.postValue(null)
        }
        else{
            selectedSportType.postValue(sportType)
            computedCaloriesLiveData.postValue("${sportType.caloriesBurnedPerMinute} kalori per menit")
        }
    }

    fun onMulaiClick() {
        if (selectedSportType.value == null) {
            _toastLiveData.postValue("Mohon pilih olahraga terlebih dahulu")
        } else {
            isContinue.postValue(true);
        }
    }

    init {
        Timber.d("init NewExerciseViewModel")

        sportTypeListLiveData = launchOnViewModelScope {
            isLoading.set(true)
            this.sportTypeRepository.fetchSportTypeList(onSuccess = { isLoading.set(false) })
                .asLiveData()
        }
    }
    


}