package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence

import androidx.room.*
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.ExerciseAndSportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType


@Dao
interface SportTypeDao {
    @Query("SELECT * FROM SportType")
    suspend fun getSportTypeList() : List<SportType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllSportType(sporttypes: List<SportType?>?)

    @Transaction
    @Query("SELECT * FROM SportType WHERE sportTypeId IN (SELECT DISTINCT(sportTypeDoneId) FROM Exercise)")
    fun getExerciseAndSportTypeList() : List<ExerciseAndSportType>
}