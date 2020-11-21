package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository

import androidx.annotation.WorkerThread
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.ExerciseAndSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.SportTypeDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SportTypeRepository @Inject constructor(
    private val sportTypeDao: SportTypeDao
) : Repository{

    @WorkerThread
    suspend fun fetchSportTypeList(
        onSuccess: () -> Unit
    ) = flow<List<SportType>> {
        val sporttypes = sportTypeDao.getSportTypeList()
        emit(sporttypes)
        onSuccess()
    }.flowOn(Dispatchers.IO)

    @WorkerThread
    suspend fun fetchSportTypeAndExerciseList(
        onSuccess: () -> Unit
    ) = flow<List<ExerciseAndSportType>>{
        val exercises = sportTypeDao.getExerciseAndSportTypeList()
        emit(exercises)
        onSuccess()
    }.flowOn(Dispatchers.IO)
}