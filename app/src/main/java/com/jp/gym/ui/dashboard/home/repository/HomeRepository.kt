package com.jp.gym.ui.dashboard.home.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jp.gym.utils.preference.SaveSharedPreference

class HomeRepository (private var context: Context) {
    private var firestore = FirebaseFirestore.getInstance()
    private var getExpenseAmount: Int = 0
    private var countOfActiveMembers: Int = 0
    private var countOfInActiveMembers: Int = 0
    private var duePaymentAmount: Int = 0
    private var receivedPaymentAmount: Int = 0
    private var countOfTotalMembers : Int = 0
    lateinit var query: Query
    val sharedPreference = SaveSharedPreference()
    val userId = sharedPreference.getUserId(context)

    fun fetchTotalMembers(totalMembers : MutableLiveData<Int>, activeUserCount: MutableLiveData<Int>, inActiveUserCount: MutableLiveData<Int>){

        firestore.collection("Members")
            .get()
            .addOnCompleteListener { task ->
                var document = task.result!!.documents
                for(i in 0..document.size-1){
                    val snap = document.get(i)
                    val getPaymentStatus: String = snap?.get("paymentStatus").toString()
                    if(getPaymentStatus == "Paid"){
                        countOfActiveMembers++
                        activeUserCount.postValue(countOfActiveMembers)
                    }else{
                        countOfInActiveMembers++
                        inActiveUserCount.postValue(countOfInActiveMembers)
                    }
                }
                countOfTotalMembers = task.result!!.documents.size
                totalMembers.postValue(countOfTotalMembers)
            }
    }

    fun fetchTotalExpense(totalExpense: MutableLiveData<Int>) {
        firestore.collection("Trainer").document(userId).collection("Gym Expenses")
            .get()
            .addOnCompleteListener { getexpense ->
                if (getexpense.isSuccessful) {
                    val documentsdata = getexpense.result?.documents
                    for (i in 0..documentsdata?.size!! - 1) {
                        val snap = documentsdata.get(i)
                        val getItemExpense: String = snap?.get("expense_money").toString()
                        Log.d("HomeRepo ", "$getItemExpense")
                        getExpenseAmount += getItemExpense.toInt()
                        totalExpense.postValue(getExpenseAmount)
                    }
                }
            }
    }

//
    fun fetchDueAndReceivedPaymentAmount(
        dueAmount: MutableLiveData<Int>,
        receivedAmount: MutableLiveData<Int>
    ) {
        query = firestore.collection("Members").orderBy("amount", Query.Direction.DESCENDING)
        query.get()
            .addOnCompleteListener { tasknew ->
                if (tasknew.isSuccessful) {
                    val documentsdata = tasknew.result?.documents
                    for (i in 0..documentsdata?.size!! - 1) {
                        val snap = documentsdata.get(i)
                        val getItemExpense: String = snap?.get("amount").toString()
                        if (getItemExpense.toInt() < 0) {
                            duePaymentAmount += getItemExpense.toInt()
                            dueAmount.postValue(-duePaymentAmount)
                        } else {
                            receivedPaymentAmount += getItemExpense.toInt()
                            receivedAmount.postValue(receivedPaymentAmount)
                        }
                    }

                }
            }
    }
}