package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.Exercise
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.ExerciseAndSportType

@Dao
interface ExerciseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercise(exercise: Exercise)

    @Query("SELECT * FROM Exercise where page=:page_")
    suspend fun getExerciseList(page_: Int): List<Exercise>

    @Query("SELECT * FROM ExerciseSportType")
    suspend fun getExerciseSportTypeList() : List<ExerciseSportType>

}