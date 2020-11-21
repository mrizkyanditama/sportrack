package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.persistence

import androidx.room.DatabaseView
import java.util.*

@DatabaseView("SELECT Exercise.dateOfExercise, Exercise.minutesExercise, Exercise.caloriesBurned," +
        "SportType.name AS sportName, SportType.imgPath as imgPath FROM Exercise " +
        "INNER JOIN SportType ON Exercise.sportTypeDoneId = SportType.sportTypeId")
data class ExerciseSportType(
    val dateOfExercise: Date?,
    val minutesExercise: Int?,
    val caloriesBurned: Long?,
    val sportName: String?,
    val imgPath: String?
){
    fun getMinutes(): String = "$sportName selama $minutesExercise menit"
    fun getTotalCaloriesBurned(): String = "Kalori terbakar: $caloriesBurned kalori"
}