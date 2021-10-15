package kr.ac.jbnu.moonlader.controller.models

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.controlwear.virtual.joystick.android.JoystickView
import kr.ac.jbnu.moonlader.R
import kr.ac.jbnu.moonlader.controller.View.ControllerView
import kr.ac.jbnu.moonlader.controller.helper.ControllerHelper
import kr.ac.jbnu.moonlader.userManagement.Helper.UserManagement

class ControllerViewModel(context : Context, view : ControllerView) : ViewModel() {
    private val helper = ControllerHelper(context, this)
    private val view = view
    var status = MutableLiveData<String>()

    init{
        helper.getGameStatus()
    }

    fun updateStatus(status : String){
        this.status.value = status
    }

    fun controllerHandler() {
        view.controller.setOnMoveListener { angle, strength ->
            Log.d("controller", angle.toString())
            if(angle == 0){
                helper.updateAxis(0.0, 0.0)
            }

            else{
                helper.updateAxis(view.controller.normalizedX.toDouble(), view.controller.normalizedY.toDouble())
            }
        }
    }

    fun onButtonClick(view: View) {
        when (view.id) {
            R.id.btn_startGame -> helper.requestGameSTART()
        }
    }
}