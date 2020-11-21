package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Entity
data class Goal(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "period_start") val periodStart: Date,
    @ColumnInfo(name="period_end") val periodEnd: Date,
    @ColumnInfo(name="cal_target") val calTarget: Int
) {
    fun getCalTarget(): String = "$calTarget kalori"
}