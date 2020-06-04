package com.jp.gym.ui.dashboard.home.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class HomeRepository {
    var firestore = FirebaseFirestore.getInstance()
    var getExpenseAmount : Int =0
    var countOfActiveMembers : Int = 0
    var countOfInActiveMember : Int = 0
    var duePaymentAmount : Int = 0
    var receivedPaymentAmount : Int = 0
    lateinit var query: Query

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
                        getExpenseAmount = getExpenseAmount + getitemexpense.toInt()
                        totalExpense.postValue(getExpenseAmount)
                    }
                }
            }
    }

    fun fetchUserCount(activeUserCount : MutableLiveData<Int>){
        firestore.collection("Members")
            .whereEqualTo("paymentStatus", "Paid")
            .get()
            .addOnSuccessListener { task ->
                for (document in task) {
                    countOfActiveMembers++
                    activeUserCount.postValue(countOfActiveMembers)
                }
            }
    }

    fun fetchInActiveUserCount(inActiveUserCount : MutableLiveData<Int>){
        firestore.collection("Members")
            .whereEqualTo("paymentStatus", "Due")
            .get()
            .addOnSuccessListener { task ->
                for (document in task) {
                    countOfInActiveMember++
                    inActiveUserCount.postValue(countOfInActiveMember)
                }
            }
    }

    fun fetchDueAndReceivedPaymentAmount(dueAmount : MutableLiveData<Int>,receivedAmount : MutableLiveData<Int>){
        query = firestore.collection("Members").orderBy("amount", Query.Direction.DESCENDING)
        query.get()
            .addOnCompleteListener { tasknew ->
                if (tasknew.isSuccessful) {
                    var documentsdata = tasknew.result?.documents
                    for (i in 0..documentsdata?.size!! - 1) {
                        var snap = documentsdata.get(i)
                        var getItemExpense: String = snap?.get("amount").toString()
                        if(getItemExpense.toInt()<0){
                            duePaymentAmount = duePaymentAmount + getItemExpense.toInt()
                            dueAmount.postValue(-duePaymentAmount)
                        }else {
                            receivedPaymentAmount = receivedPaymentAmount + getItemExpense.toInt()
                            receivedAmount.postValue(receivedPaymentAmount)
                        }
                    }

                }
            }
    }
}