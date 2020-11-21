package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.repository

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.Exercise
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence.ExerciseDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import javax.inject.Inject

class ExerciseRepository @Inject constructor(
    private val exerciseDao: ExerciseDao
) : Repository {

    @WorkerThread
    suspend fun fetchExerciseList(
        page: Int,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) = flow {
        val exercises = exerciseDao.getExerciseList(page)
        emit(exercises)
        onSuccess()
    }.flowOn(Dispatchers.IO)

    suspend fun fetchExerciseSportTypeList(
        onSuccess: () -> Unit
    ) = flow {
        val exercises = exerciseDao.getExerciseSportTypeList()
        emit(exercises)
        onSuccess()
    }.flowOn(Dispatchers.IO)

    fun insertExercise(exercise: Exercise,  onSuccess: () -> Unit){
        Timber.d("MASUK PLIS")
        exerciseDao.insertExercise(exercise)
        onSuccess()
    }
}