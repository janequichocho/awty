package edu.us.ischool.janeq97.arewethereyet

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.util.Log
import android.widget.Button
import android.widget.Toast
import java.util.*
import java.util.TimerTask


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
        } else {
            var message = messageView?.text.toString()
            var phoneNumber = phoneNumView?.text.toString()
            var time = timeView?.text.toString()

            if (message != "" && phoneNumber != "" && time != "") {
                var minutes = time.toInt()


                var timerTask = Task(this, phoneNumber)
                timerActive = true
                timer?.schedule(timerTask, (minutes * 60000).toLong(), (minutes * 60000).toLong())

                (view as Button).setText("Stop")
            }
        }
    }
}



class Task(val context: Context, val phoneNum: String): TimerTask() {

    override fun run() {
        (context as Activity).runOnUiThread(object: Runnable {
            override fun run() {
                Toast.makeText(context, "$phoneNum: Are we there yet?", Toast.LENGTH_LONG).show()
            }
        })

    }
}
