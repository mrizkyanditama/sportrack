package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.start_exercise

import android.app.*
import android.app.PendingIntent.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.RemoteViews
import androidx.activity.viewModels
import androidx.annotation.NonNull
import androidx.annotation.VisibleForTesting
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.base.DataBindingActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.databinding.StartExerciseBinding
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.extensions.argument
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.model.SportType
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.broadcast_receiver.ExerciseBroadcastReceiver
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeActivity
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class StartExerciseActivity : DataBindingActivity() {
    @Inject
    lateinit var startExerciseViewModelFactory: StartExerciseViewModel.AssistedFactory
    private val binding: StartExerciseBinding by binding(R.layout.start_exercise)
    private val sportTypeItem: SportType by argument("SPORT_TYPE")

    lateinit var notificationManager : NotificationManager
    lateinit var intentFilter: IntentFilter
    lateinit var receiver: ExerciseBroadcastReceiver

    @VisibleForTesting
    val viewModel: StartExerciseViewModel by viewModels {
        StartExerciseViewModel.provideFactory(startExerciseViewModelFactory, sportTypeItem)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.apply {
            lifecycleOwner = this@StartExerciseActivity
            sporttype = sportTypeItem
            vm = viewModel
        }
        val doneObserver = Observer<Boolean> {
            if (it == true){
                val intent = Intent(this, HomeActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                finish()
                startActivity(intent)
            }
        }
        viewModel.isFinished.observe(this, doneObserver)
        notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notifObserver = Observer<Boolean> {
            if (it == true){
                Intent().also { intent ->
                    intent.setAction("id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.NEW_NOTIFICATION")
                    intent.putExtra("data", viewModel.notificationMessage.value)
                    Timber.d("MASUK MENNN")
                    sendBroadcast(intent)
                }
            }
        }
        viewModel.isNotification.observe(this, notifObserver)

        receiver = ExerciseBroadcastReceiver()
        intentFilter = IntentFilter("id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.NEW_NOTIFICATION");
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }

    override fun onPause() {
        super.onPause()
        registerReceiver(receiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        registerReceiver(receiver, intentFilter)
    }


}
