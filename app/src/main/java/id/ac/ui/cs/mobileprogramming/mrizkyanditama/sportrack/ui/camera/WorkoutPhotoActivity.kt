package id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.camera

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.material.snackbar.Snackbar
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.R
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.ui.camera.CameraPreviewActivity
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.utils.checkSelfPermissionCompat
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.utils.requestPermissionsCompat
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.utils.shouldShowRequestPermissionRationaleCompat
import id.ac.ui.cs.mobileprogramming.mrizkyanditama.sportrack.utils.showSnackbar
import kotlinx.android.synthetic.main.camera_home.*

const val PERMISSION_REQUEST_CAMERA = 0

class WorkoutPhotoActivity : AppCompatActivity(), ActivityCompat.OnRequestPermissionsResultCallback {
    private val IMAGE_CAPTURE_CODE = 1001

    var image_uri: Uri? = null

    private lateinit var layout: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.camera_home)
        layout = findViewById(R.id.main_layout)

        // Register a listener for the 'Show Camera Preview' button.
        findViewById<Button>(R.id.button_open_camera).setOnClickListener { showCameraPreview() }
    }

    override fun onRequestPermissionsResult(
            requestCode: Int,
            permissions: Array<String>,
            grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                layout.showSnackbar(R.string.camera_permission_granted, Snackbar.LENGTH_SHORT)
                openCamera()
            } else {
                // Permission request was denied.
                layout.showSnackbar(R.string.camera_permission_denied, Snackbar.LENGTH_SHORT)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK){
            //set image captured to image view
            image_view.setImageURI(image_uri)
        }
    }

    private fun showCameraPreview() {
        // Check if the Camera permission has been granted
        if (checkSelfPermissionCompat(Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            layout.showSnackbar(R.string.camera_permission_available, Snackbar.LENGTH_SHORT)
            openCamera()
        } else {
            // Permission is missing and must be requested.
            requestCameraPermission()
        }
    }

    private fun requestCameraPermission() {
        // Permission has not been granted and must be requested.
        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.CAMERA)) {
            // Provide an additional rationale to the user if the permission was not granted
            // and the user would benefit from additional context for the use of the permission.
            // Display a SnackBar with a button to request the missing permission.
            layout.showSnackbar(R.string.camera_access_required,
                    Snackbar.LENGTH_INDEFINITE, R.string.ok) {
                requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA),
                        PERMISSION_REQUEST_CAMERA)
            }

        } else {
            layout.showSnackbar(R.string.camera_permission_not_available, Snackbar.LENGTH_SHORT)

            // Request the permission. The result will be received in onRequestPermissionResult().
            requestPermissionsCompat(arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST_CAMERA)
        }
    }

    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        //camera intent
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        startActivityForResult(cameraIntent, IMAGE_CAPTURE_CODE)
    }

//    private fun startCamera() {
//        val intent = Intent(this, CameraPreviewActivity::class.java)
//        startActivity(intent)
//
//    }
}