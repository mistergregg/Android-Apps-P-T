package com.gbreed.snapchatclone

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var userEditText: EditText? = null
    var passEditText: EditText? = null
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userEditText = findViewById(R.id.editTextUser)
        passEditText = findViewById(R.id.editTextPass)

        if (mAuth.currentUser != null) {
            login()
        }
    }

    fun loginUser(view: View)
    {
        mAuth.signInWithEmailAndPassword(userEditText?.text.toString(), passEditText?.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    login()
                } else {
                    mAuth.createUserWithEmailAndPassword(userEditText?.text.toString(), passEditText?.text.toString()).addOnCompleteListener(this) { task ->
                        if(task.isSuccessful)
                        {
                            FirebaseDatabase.getInstance().getReference().child("users").child(task.result!!.user.uid).child("email").setValue(userEditText?.text.toString())
                            login()
                        }else
                        {
                            Toast.makeText(this, "Login Failed Try Again", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
    }

    fun login()
    {
        val intent = Intent(this, SnapsActivity::class.java)
        startActivity(intent)
    }
}
