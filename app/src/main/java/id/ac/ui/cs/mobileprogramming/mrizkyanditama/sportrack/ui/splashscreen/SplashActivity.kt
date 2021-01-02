package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.splashscreen

import android.app.Activity
import android.content.Intent
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.os.Handler
import android.util.Log
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.home.HomeActivity
import java.util.*
import kotlin.concurrent.schedule


class SplashActivity : Activity() {
    private var mGLView: GLSurfaceView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity
        mGLView = MyGLSurfaceView(this)
        setContentView(mGLView)
        val intent = Intent(this, HomeActivity::class.java)
        Timer("Delay Splash", false).schedule(2000) {
            startActivity(intent)
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        mGLView!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        mGLView!!.onResume()
    }
}