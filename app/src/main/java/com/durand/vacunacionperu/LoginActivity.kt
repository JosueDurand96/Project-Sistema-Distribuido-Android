package com.durand.vacunacionperu

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var usuario = "josue"

    private lateinit var view: ConstraintLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        view = findViewById(R.id.snackLogin)

        loginButton.setOnClickListener {
            initSession()
        }

    }


    private fun initSession() {
        if (userEditText.text!!.isEmpty()) {
            userEditText.error = "Debe ingresar su usuario!"
        } else if (passwordEditText.text!!.isEmpty()) {
            passwordEditText.error = "Debe ingresar su contraseña!"
        } else {
            if (userEditText.text.toString() == usuario) {
                val intent = Intent(applicationContext, MenuActivity::class.java)
                startActivity(intent)
            } else {
                Snackbar.make(view, "Usuario no encontrado!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(Color.RED).show()
            }
        }
    }
}