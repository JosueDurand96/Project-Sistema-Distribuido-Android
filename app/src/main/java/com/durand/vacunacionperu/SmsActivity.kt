package com.durand.vacunacionperu

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import kotlinx.android.synthetic.main.activity_sms.*
import java.util.concurrent.TimeUnit

class SmsActivity : AppCompatActivity() {

    private var forceResendingToken: PhoneAuthProvider.ForceResendingToken? = null
    private var mCallBacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks? = null
    private var mVerificationId: String? = null
    private lateinit var firebaseAuth: FirebaseAuth
    private val TAG = "MAIN_TAG"

    //Progress DIALOG
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sms)
        firstConstraintLayout.visibility = View.VISIBLE
        secondConstraintLayout.visibility = View.GONE

        //init
        firebaseAuth = FirebaseAuth.getInstance()
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Espere por favor!")
        progressDialog.setCanceledOnTouchOutside(false)

        mCallBacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
               Log.d(TAG,"$phoneAuthCredential")
                signInWithPhoneAuthCredential(phoneAuthCredential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.d(TAG,"$e")
                progressDialog.dismiss()
                Toast.makeText(this@SmsActivity, "${e.message}", Toast.LENGTH_LONG).show()
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                Log.d(TAG,"$verificationId")
                Log.d(TAG,"$token")
                mVerificationId = verificationId
                forceResendingToken = token
                progressDialog.dismiss()

                firstConstraintLayout.visibility = View.VISIBLE
                secondConstraintLayout.visibility = View.VISIBLE
                Toast.makeText(this@SmsActivity, "Verificando código!", Toast.LENGTH_LONG).show()

            }
        }

        sendButton.setOnClickListener {

            val phone = phoneEdittext.text.toString()
            if (phone.isEmpty()){
                phoneEdittext.error = "Debe ingresar un número celular!"
            }else{
                Log.d(TAG,"$phone")

                startPhoneNumberVerification(phone)
            }
        }

        resetTextView.setOnClickListener {
            val phone = phoneEdittext.text.toString()
            if (phone.isEmpty()){
                phoneEdittext.error = "Debe ingresar un número celular!"
            }else{
                Log.d(TAG,"$phone")
                Log.d(TAG,"${forceResendingToken.toString()}")
                resendVerificationCode(phone, forceResendingToken)
            }
        }
        continueButton.setOnClickListener {
            val code = codeEditText.text.toString()
            if (code.isEmpty()){
                phoneEdittext.error = "Debe ingresar un número celular!"
            }else{
                Log.d(TAG,"$code")
                Log.d(TAG,"${mVerificationId.toString()}")
                verifyPhoneNumberWithCode(mVerificationId, code)
            }

        }
    }

    private fun startPhoneNumberVerification(phone: String) {
        progressDialog.setMessage("Verificando Celular...")
        progressDialog.show()

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+51$phone")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBacks)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun resendVerificationCode(
        phone: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        progressDialog.setMessage("Reenviando código...")
        progressDialog.show()

        val options = PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber("+51$phone")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(mCallBacks)
            .setForceResendingToken(token)
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        progressDialog.setMessage("Verificando código...")
        progressDialog.show()

        val credential = PhoneAuthProvider.getCredential(verificationId, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        progressDialog.setMessage("Ingresando ...")

        firebaseAuth.signInWithCredential(credential)
            .addOnSuccessListener {
                progressDialog.dismiss()
                val phone = firebaseAuth.currentUser.phoneNumber
                Toast.makeText(this, "Se logro identificar con este cel: $phone", Toast.LENGTH_LONG).show()
                startActivity(Intent(this, MenuActivity::class.java))
            }
            .addOnFailureListener { e ->
                progressDialog.dismiss()
                Toast.makeText(this, "${e.message}", Toast.LENGTH_LONG).show()
            }
    }
}