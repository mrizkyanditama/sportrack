package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model

import androidx.room.Embedded
import androidx.room.Relation

data class ExerciseAndSportType(
    @Embedded val sportType: SportType,
    @Relation(
        parentColumn = "sportTypeId",
        entityColumn = "sportTypeDoneId"
    )
    val exercises: List<Exercise>
){
    fun getMinutes(): String = "${sportType.name} selama ${exercises[0].minutesExercise} menit"
    fun getTotalCaloriesBurned(): String = "Kalori terbakar: ${exercises[0].caloriesBurned} kalori"
}