package com.durand.vacunacionperu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var usuario = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuario = userEditText.text.toString()
        password = passwordEditText.text.toString()


        loginButton.setOnClickListener {
            initSession()
        }

    }


    private fun initSession() {
        if (usuario.isEmpty() && password.isEmpty()) {
            userEditText.error = "Debe ingresar su usuario!"
            passwordEditText.error = "Debe ingresar su contraseña!"
        } else {
            if (usuario == usuario) {
                val intent = Intent(applicationContext, MenuActivity::class.java)
                startActivity(intent)
            }
        }
    }
}