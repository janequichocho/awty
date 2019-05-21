package edu.us.ischool.janeq97.arewethereyet

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.util.*
import java.util.TimerTask
import java.util.jar.Manifest


class MainActivity : AppCompatActivity() {
    var messageView: EditText? = null
    var phoneNumView: EditText? = null
    var timeView: EditText? = null
    var timer: Timer? = null
    var timerActive = false
    var handler: Handler? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageView = findViewById(R.id.message)
        phoneNumView = findViewById(R.id.phone_number)
        timeView = findViewById(R.id.time_interval)

        timer = Timer("Timer")
        handler = Handler()
    }

    fun handleStartAndStop(view: View) {
        if (timerActive) {
            timer?.cancel()
            timer?.purge()
            timerActive = false
            (view as Button).setText("Start")
        } else {
            var message = messageView?.text.toString()
            var phoneNumber = phoneNumView?.text.toString()
            var time = timeView?.text.toString()

            if (message != "" && phoneNumber != "" && time != "") {
                var minutes = time.toInt()

                var timerTask = Task(this, phoneNumber, message)
                timerActive = true
                timer?.schedule(timerTask, (minutes * 60000).toLong(), (minutes * 60000).toLong())

                (view as Button).setText("Stop")
            }
        }
    }
}



class Task(val context: Context, val phoneNum: String, var message: String): TimerTask() {

    override fun run() {
        (context as Activity).runOnUiThread(object: Runnable {
            override fun run() {
                if (ContextCompat.checkSelfPermission(
                        context,
                        android.Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(context, arrayOf(android.Manifest.permission.SEND_SMS), 1)
                } else {
                    val smsManager = SmsManager.getDefault()
                    val number = phoneNum
                    val message = "Are we there yet?"
                    smsManager.sendTextMessage(
                        number,
                        null,
                        message,
                        null,
                        null
                    )
                }
            }
        })
    }
}