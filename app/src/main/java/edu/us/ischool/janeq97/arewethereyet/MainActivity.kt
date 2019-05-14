package edu.us.ischool.janeq97.arewethereyet

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.util.Log
import android.widget.Button

class MainActivity : AppCompatActivity() {
    var messageView: EditText? = null
    var phoneNumView: EditText? = null
    var timeView: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageView = findViewById(R.id.message)
        phoneNumView = findViewById(R.id.phone_number)
        timeView = findViewById(R.id.time_interval)
    }

    fun handleStartAndStop(view: View) {
        var message = messageView?.text.toString()
        var phoneNumber = phoneNumView?.text.toString()
        var time = timeView?.text.toString()

        if (message != "" && phoneNumber != "" && time != "") {


            (view as Button).setText("Stop")
        }
    }
}
