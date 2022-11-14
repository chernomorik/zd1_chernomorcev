package com.bignerdranch.android.zd1_chernomorcev

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

const val APP_PREFERENCES = "mysettings"
const val APP_PREFERENCES_LOG = "log"
const val APP_PREFERENCES_PAS = "pas"

class MainActivity : AppCompatActivity() {
    lateinit var buttonsave: Button
    lateinit var login: EditText
    lateinit var password: EditText
    lateinit var settings: SharedPreferences
    var hasVisited: Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settings = getSharedPreferences(APP_PREFERENCES_LOG, Context.MODE_PRIVATE)
        login = findViewById(R.id.login)
        password = findViewById(R.id.password)
        buttonsave = findViewById(R.id.butin)
        hasVisited = settings.getBoolean("hasVisited", false)
        if (hasVisited == false) {
            val x: SharedPreferences.Editor = settings.edit()
            x.putBoolean("hasVisited", true)
            x.apply()
            buttonsave.visibility = View.INVISIBLE
        } else {
            buttonsave.visibility = View.VISIBLE
        }
        buttonsave.setOnClickListener {
            var log = login.getText().toString()
            var prefEditor = settings.edit()
            prefEditor.putString(APP_PREFERENCES_LOG, log)
            prefEditor.apply()
            val toast = Toast.makeText(applicationContext, "Данные сохранены!", Toast.LENGTH_SHORT)
            toast.show()
            var username = login.getText().toString()
            var getuser = settings.getString(APP_PREFERENCES_LOG, "noLog")
            var pass = settings.getString(APP_PREFERENCES_PAS, "noPas")
            if (login.text.toString().isNotEmpty() || password.text.toString().isNotEmpty()) {

                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)


            } else {
                val alert = AlertDialog.Builder(this)
                    .setTitle("Ошибка")
                    .setMessage("У вас есть незаполненные поля")
                    .setPositiveButton("Ok", null)
                    .create()
                    .show()
                }
        }
    }
}