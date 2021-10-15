package kr.ac.jbnu.moonlader.userManagement.Helper

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Message
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat.startActivity
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kr.ac.jbnu.moonlader.framework.Helper.AES256Util
import kr.ac.jbnu.moonlader.framework.Helper.DialogManager
import kr.ac.jbnu.moonlader.framework.View.NavigationViewManager
import kr.ac.jbnu.moonlader.userManagement.models.SignInViewModel

open class UserManagement(context : Context) : AES256Util(){
    private val auth = Firebase.auth
    private val db = FirebaseFirestore.getInstance()
    private val TAG = "UserManagement"
    private val context = context
    private val dialog = DialogManager(context)

    private lateinit var userRef : DocumentReference

    protected fun signIn(email : String, password : String){
        dialog.showProgress("처리 중...")

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    dialog.dismissDialog()

                    SignInViewModel(context).changeView()
                }

                else{
                    dialog.dismissDialog()

                    try{
                        Log.d(TAG, task.exception.toString())

                        throw task.exception!!
                    } catch(e : FirebaseAuthInvalidCredentialsException){
                        dialog.showAlert("존재하지 않는 계정", "계정이 존재하지 않습니다.")
                    }
                }
            }
    }

    protected fun signUp(email : String, password: String, nickName : String){
        dialog.showProgress("처리 중...")

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    dialog.dismissDialog()

                    userRef = db.collection("Users").document(auth.currentUser?.uid.toString())

                    val data = hashMapOf("mail" to aesEncode(email),
                    "nickName" to aesEncode(nickName))

                    userRef.set(data).addOnSuccessListener {
                        dialog.showAlert("가입 완료", "회원 가입이 완료되었습니다.")
                    }.addOnFailureListener {
                        dialog.dismissDialog()

                        try{
                            Log.w(TAG, it)
                            throw it
                        } catch(e : FirebaseAuthEmailException){
                            dialog.showAlert("올바르지 않는 형식의 E-Mail", "올바른 형식의 E-Mail을 입력하세요.")
                        }
                    }
                }
            }
    }
}