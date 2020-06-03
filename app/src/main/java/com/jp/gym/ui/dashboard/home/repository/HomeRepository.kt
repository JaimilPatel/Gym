package com.jp.gym.ui.dashboard.home.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore

class HomeRepository {
    var firestore = FirebaseFirestore.getInstance()
    var getexpenseamount : Int =0
    fun fetchTotalExpense(totalExpense : MutableLiveData<Int>){
        firestore.collection("Trainer").document("12").collection("Gym Expenses")
            .get()
            .addOnCompleteListener { getexpense->
                if(getexpense.isSuccessful){
                    var documentsdata = getexpense.result?.documents
                    for (i in 0..documentsdata?.size!! - 1) {
                        var snap = documentsdata.get(i)
                        var getitemexpense: String = snap?.get("expense_money").toString()
                        Log.d("HomeRepo ","$getitemexpense")
                        getexpenseamount = getexpenseamount + getitemexpense.toInt()
                        totalExpense.postValue(getexpenseamount)
                    }
                }
            }
    }
}