package com.jericoluna.firestore

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.location.LocationListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import java.security.MessageDigest

class MainActivity : AppCompatActivity() {


    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var firebaseAuthListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    /*

    solo fue sacar el hash
        try{
            val info:PackageInfo  = getPackageManager().getPackageInfo(
                "com.juanpablo.firestore",
                PackageManager.GET_SIGNATURES
            )
            info.signatures.forEach {
              val md:MessageDigest = MessageDigest.getInstance("SHA")
                md.update(it.toByteArray())
                Log.d("keyHash: ", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }


        } catch(e:Exception ){
            e.printStackTrace()
        }


     */

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseAuthListener=FirebaseAuth.AuthStateListener {

            if(it.currentUser!=null){

            }
            else{
                val auth =AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false)
                    .setTosAndPrivacyPolicyUrls("","")//terminos y condiciones, ademas de aviso de privacidad
                    .setLogo(R.mipmap.ic_launcher)
                    .setAlwaysShowSignInMethodScreen(true)
                    .setAvailableProviders(
                        arrayListOf(
                            AuthUI.IdpConfig.EmailBuilder().setRequireName(true).build(),
                            AuthUI.IdpConfig.GoogleBuilder().build(),
                            AuthUI.IdpConfig.FacebookBuilder().setPermissions(
                                listOf("public_profile")
                            ).build()
                            )
                    ).build()
                startActivityForResult(auth,101)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        firebaseAuth.addAuthStateListener ( firebaseAuthListener )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101 && resultCode==Activity.RESULT_OK){
            Toast.makeText(this,"Bienvenido",Toast.LENGTH_SHORT).show()
        }
    }
}
