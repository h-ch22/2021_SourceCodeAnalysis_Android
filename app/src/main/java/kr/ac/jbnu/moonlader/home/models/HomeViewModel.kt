package kr.ac.jbnu.moonlader.home.models

import android.util.Log
import androidx.databinding.ObservableArrayMap
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kr.ac.jbnu.moonlader.framework.Helper.AES256Util

class HomeViewModel : ViewModel(){
    private val db = FirebaseFirestore.getInstance()
    private val aeS256Util = AES256Util()
    var rankList : ObservableArrayMap<Int, RankModel> = ObservableArrayMap()
    var localRankList : HashMap<String, RankModel> = HashMap<String, RankModel>()

    fun getRank(){
        val queryRef = db.collection("Users").orderBy("maxScore", Query.Direction.ASCENDING).limit(3)

        queryRef.addSnapshotListener{snapshot, error ->
            if(error != null){
                Log.w("RankSnapshot", error)

                return@addSnapshotListener
            }

            if(snapshot != null){
                for(document in snapshot.documentChanges){
                    when (document.type){
                        DocumentChange.Type.ADDED -> {
                            aeS256Util.aesDecode(document.document.get("nickName").toString())?.let { RankModel(it, document.document.get("maxScore").toString(), document.document.id) }
                                ?.let {
                                    localRankList.put(document.document.id,
                                        it
                                    )
                                }

                            queryData()
                        }

                        DocumentChange.Type.MODIFIED -> {
                            localRankList.remove(document.document.id)

                            aeS256Util.aesDecode(document.document.get("nickName").toString())?.let { RankModel(it, document.document.get("maxScore").toString(), document.document.id) }
                                ?.let {
                                    localRankList.put(document.document.id,
                                        it
                                    )
                                }


                            queryData()
                        }

                        else -> return@addSnapshotListener
                    }
                }
            }
        }
    }

    private fun queryData(){
        val data = localRankList.entries.iterator()
        var i = 1

        while(data.hasNext()){

            if(rankList.get(i)?.score == null){
                rankList.put(i, data.next().value)
            }

            else{
                if(rankList.get(i)?.score?.toInt()!! < data.next().value.score.toInt()){
                    rankList.put(i, data.next().value)
                }
            }

            i+=1
        }
    }
}