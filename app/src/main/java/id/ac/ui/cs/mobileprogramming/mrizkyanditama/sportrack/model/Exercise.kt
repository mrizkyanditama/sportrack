package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Exercise(
    var page: Long = 0,
    @PrimaryKey(autoGenerate = true) val exerciseId: Long?,
    val dateOfExercise: Date,
    val minutesExercise: Int,
    val caloriesBurned: Long,
    val sportTypeDoneId: Long
)