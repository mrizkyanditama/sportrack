package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class SportType(
    @PrimaryKey val sportTypeId: Long,
    val name: String,
    val imgPath: String,
    val caloriesBurnedPerMinute: Long
) : Parcelable