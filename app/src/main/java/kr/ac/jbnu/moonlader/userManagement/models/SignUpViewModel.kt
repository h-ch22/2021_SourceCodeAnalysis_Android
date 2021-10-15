package kr.ac.jbnu.moonlader.userManagement.models

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import kr.ac.jbnu.moonlader.R
import kr.ac.jbnu.moonlader.framework.Helper.DialogManager
import kr.ac.jbnu.moonlader.userManagement.Helper.UserManagement

class SignUpViewModel(context : Context) : UserManagement(context){
    val field_email = ObservableField<String>()
    val field_password = ObservableField<String>()
    val field_checkPassword = ObservableField<String>()
    val field_nickName = ObservableField<String>()

    private val dialog = DialogManager(context)

    private var email : String?
        get() = field_email.get()
        set(value) = field_email.set(value)

    private var password : String?
        get() = field_password.get()
        set(value) = field_password.set(value)

    private var checkPassword : String?
        get() = field_checkPassword.get()
        set(value) = field_checkPassword.set(value)

    private var nickName : String?
        get() = field_nickName.get()
        set(value) = field_nickName.set(value)

    fun onButtonClick(view : View){
        when(view.id){
            R.id.btn_signUp -> {
                if (email == null || password == null || checkPassword == null || nickName == null){
                    dialog.showAlert("공백 필드", "모든 필드를 채워주세요.")
                }

                else if (password != checkPassword){
                    dialog.showAlert("일치하지 않는 비밀번호", "비밀번호가 일치하지 않습니다.")
                }

                else if(password!!.length < 6){
                    dialog.showAlert("취약한 비밀번호", "보안을 위해 6자리 이상의 비밀번호를 입력하세요.")
                }

                else{
                    signUp(email!!, password!!, nickName!!)
                }
            }
        }
    }
}