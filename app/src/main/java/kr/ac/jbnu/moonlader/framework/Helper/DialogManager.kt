package kr.ac.jbnu.moonlader.framework.Helper

import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

class DialogManager(context : Context) {
    private val context = context
    private val dialog = ProgressDialog(context, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar)

    fun showAlert(title : String, contents : String){
        val alertBuilder = AlertDialog.Builder(context, android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar_MinWidth)
        alertBuilder.setTitle(title)
        alertBuilder.setMessage(contents)
        alertBuilder.setPositiveButton("확인", DialogInterface.OnClickListener{ dialog, which ->

        })

        alertBuilder.show()
    }

    fun showProgress(contents : String){
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        dialog.setMessage(contents)
        dialog.show()
    }

    fun dismissDialog(){
        dialog.dismiss()
    }
}