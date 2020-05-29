package com.jp.gym.ui.dashboard.expense.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.messaging.FirebaseMessaging
import com.jp.gym.ui.dashboard.expense.model.Expense

class ExpenseRepository {

    var firestore= FirebaseFirestore.getInstance()
    lateinit var query: Query
    lateinit var expenseArrayList: ArrayList<Expense>
    var expenselistdata: MutableLiveData<ArrayList<Expense>> = MutableLiveData()
    lateinit var lastvisible: DocumentSnapshot

    fun addExpenseToDatabase(date :String ,trainerName :String , itemName : String , expenseMoney : String, userId : String){

        val addExpenseCollection : HashMap<String,String> = hashMapOf(
            DATE_KEY to date,
            TRAINER_NAME_KEY to trainerName,
            PURCHASE_ITEM_KEY to itemName,
            PURCHASE_EXPENSE_KEY to expenseMoney)

        firestore.collection("Trainer").document(""+userId).collection("Gym Expenses")
            .add(addExpenseCollection)


    }

    fun getExpenseFromDatabase(expenseList : MutableLiveData<ArrayList<Expense>>){

        query = FirebaseFirestore.getInstance().collection("Trainer").document("" + "12")
            .collection("Gym Expenses").orderBy("trainer_name")

        query.get().addOnCompleteListener { task ->
            var documentsdata = task.result?.documents
            expenseArrayList = ArrayList()
            for (i in 0..documentsdata?.size!! - 1) {
                var snap = documentsdata.get(i)
                var getitemname: String = snap?.get("expense_item").toString()
                var getitemexpense: String = snap?.get("expense_money").toString()
                var trainername: String = snap?.get("trainer_name").toString()
                var expensedate: String = snap?.get("expense_date").toString()
                var userexpense: Expense = Expense(trainername, expensedate, getitemname, getitemexpense)


                if (documentsdata.size > 0) {
                    expenseArrayList.add(userexpense)
                    expenselistdata.value = expenseArrayList
                    expenseList.postValue(expenseArrayList)
                    lastvisible = task.result!!.documents[documentsdata?.size!! - 1]
                }

            }

        }
    }
    companion object{
        const val DATE_KEY = "expense_date"
        const val TRAINER_NAME_KEY = "trainer_name"
        const val PURCHASE_ITEM_KEY = "expense_item"
        const val PURCHASE_EXPENSE_KEY= "expense_money"
    }
}