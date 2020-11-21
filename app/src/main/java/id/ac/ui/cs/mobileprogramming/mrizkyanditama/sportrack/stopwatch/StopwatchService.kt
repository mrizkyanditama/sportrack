package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.stopwatch

import android.os.Handler
import android.os.SystemClock
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import timber.log.Timber

class StopwatchService {

    private var MillisecondTime: Long = 0
    private var StartTime: Long = 0
    private var TimeBuff: Long = 0
    private var UpdateTime = 0L


    private var Seconds: Int = 0
    private var Minutes: Int = 0
    private var MilliSeconds: Int = 0

    private var flag:Boolean=false

    @WorkerThread
    suspend fun start(timer: (String) -> Unit)
    = flow<String?> {
        Timber.d("masuk")
        StartTime = SystemClock.uptimeMillis()
        flag = true
        var count = 0
        while(flag){
//            UpdateTime = TimeBuff + MillisecondTime
//            Seconds = (UpdateTime / 1000).toInt()
//            Minutes = Seconds / 60
//            Seconds = Seconds % 60
//            MilliSeconds = (UpdateTime % 1000).toInt()
//            var timer = String()
//
//            if (Minutes.toString().length < 2) {
//                timer += "0" + Minutes.toString()
//            } else {
//                timer += Minutes.toString()
//            }
//            timer+= ":"
//            if (Seconds.toString().length < 2) {
//                timer += "0" + Seconds.toString()
//            } else {
//                timer += Seconds.toString()
//            }
//            timer+= ":"
//            timer += MilliSeconds.toString()
            count++
            Timber.d(count.toString())
            emit(count.toString())
            withContext(Dispatchers.Main){
                timer(count.toString())
            }
            delay(1000)
        }
    }.flowOn(Dispatchers.IO)

    suspend fun collect(timer: (String) -> Unit, time: String){
        withContext(Dispatchers.Main){
            timer(time)
        }
    }

    fun stop(){
        flag = false
    }

    fun getMinutes(): Int{
        return Minutes
    }
}