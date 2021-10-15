package kr.ac.jbnu.moonlader.controller.helper

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kr.ac.jbnu.moonlader.controller.models.ControllerViewModel
import kr.ac.jbnu.moonlader.controller.models.GameStatusChange
import kr.ac.jbnu.moonlader.controller.models.refreshGameStatus
import kr.ac.jbnu.moonlader.userManagement.Helper.UserManagement

open class ControllerHelper(context : Context, viewModel : ControllerViewModel) : UserManagement(context) {
    private val db : DatabaseReference = Firebase.database.reference
    val viewModel = viewModel
    var x = 0
    var y = 0

    fun updateAxis(x : Double, y : Double){
        val docRef = FirebaseAuth.getInstance().currentUser?.uid?.let { db.child("Controllers").child(it) }
        val data : HashMap<String, Double> = HashMap<String, Double>()

        if(x != 0.0 || y != 0.0){
            data.put("xAxis", Math.ceil(((x - 43) / 20)))
            data.put("yAxis", Math.ceil((((y - 30) / 10) * -1)))
        }

        else{
            data.put("xAxis", x)
            data.put("yAxis", y)
        }


        docRef?.setValue(data)?.addOnFailureListener {
            Log.d("ControllerHelper", it.toString())
        }
    }

    fun requestGameSTART(){
        val docRef = FirebaseAuth.getInstance().currentUser?.uid?.let { db.child("GameStatus").child(it) }
        val data : HashMap<String, String> = HashMap<String, String>()

        data.put("Status", "REQUEST TO START")

        docRef?.setValue(data)?.addOnFailureListener {
            Log.d("requestGameStart", it.toString())
        }
    }

    fun getGameStatus(){
        val docRef = FirebaseAuth.getInstance().currentUser?.uid?.let { db.child("GameStatus").child(it) }

        docRef?.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                updateStatus(snapshot.value.toString())
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                updateStatus(snapshot.value.toString())
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

    private fun updateStatus(status : String){
        when(status){
            "GAME OVER", "MAIN MENU" -> {
                viewModel.updateStatus( "게임 오버 상태입니다.")
            }

            "REQUEST TO START" -> {
                viewModel.updateStatus("게임 시작 요청 중입니다.")
            }

            "PLAYING" -> {
                getAxis()
                viewModel.updateStatus(x.toString() + " , " + y.toString())
            }
        }
    }

    fun getAxis(){
        val docRef = FirebaseAuth.getInstance().currentUser?.uid?.let { db.child("Coordinates").child(it).child("x") }

        docRef?.addChildEventListener(object : ChildEventListener{
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                if(snapshot.child("x").value != null && snapshot.child("y").value != null){
                    x = Integer.parseInt(snapshot.child("x").value.toString())
                    y = Integer.parseInt(snapshot.child("y").value.toString())
                }

            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                if(snapshot.child("x").value != null && snapshot.child("y").value != null){
                    x = Integer.parseInt(snapshot.child("x").value.toString())
                    y = Integer.parseInt(snapshot.child("y").value.toString())
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}