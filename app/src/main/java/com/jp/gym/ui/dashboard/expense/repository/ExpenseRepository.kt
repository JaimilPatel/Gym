package com.jp.gym.ui.dashboard.expense.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jp.gym.ui.dashboard.expense.database.DatabaseService
import com.jp.gym.ui.dashboard.expense.database.ExpenseData
import com.jp.gym.ui.dashboard.expense.model.Expense
import com.jp.gym.utils.preference.SaveSharedPreference

class ExpenseRepository(private var context: Context) {

    private var firestore = FirebaseFirestore.getInstance()
    private lateinit var query: Query
    private lateinit var expenseArrayList: ArrayList<Expense>
    private var expenseListData: MutableLiveData<ArrayList<Expense>> = MutableLiveData()
    private lateinit var lastVisible: DocumentSnapshot
    private lateinit var database: DatabaseService
    private lateinit var allExpenses: List<Expense>


    fun addExpenseToDatabase(
        date: String,
        trainerName: String,
        itemName: String,
        expenseMoney: String,
        userId: String
    ) {
        val addExpenseCollection: HashMap<String, String> = hashMapOf(
            DATE_KEY to date,
            TRAINER_NAME_KEY to trainerName,
            PURCHASE_ITEM_KEY to itemName,
            PURCHASE_EXPENSE_KEY to expenseMoney
        )

        firestore.collection("Trainer").document("" + userId).collection("Gym Expenses")
            .add(addExpenseCollection)


    }

    fun getExpenseFromDatabase(expenseList: MutableLiveData<ArrayList<Expense>>) {

        val sharedPreference = SaveSharedPreference()
        val userId = sharedPreference.getUserId(context)
        query = FirebaseFirestore.getInstance().collection("Trainer").document(userId)
            .collection("Gym Expenses").orderBy("trainer_name")

        query.get().addOnCompleteListener { task ->
            val documentsdata = task.result?.documents
            expenseArrayList = ArrayList()
            for (i in 0..documentsdata?.size!! - 1) {
                val snap = documentsdata[i]
                val getItemName: String = snap?.get("expense_item").toString()
                val getItemExpense: String = snap?.get("expense_money").toString()
                val trainerName: String = snap?.get("trainer_name").toString()
                val expenseDate: String = snap?.get("expense_date").toString()
                val userExpense =
                    Expense(trainerName, expenseDate, getItemName, getItemExpense)


                if (documentsdata.size > 0) {
                    expenseArrayList.add(userExpense)
                    expenseListData.value = expenseArrayList
                    expenseList.postValue(expenseArrayList)
                    lastVisible = task.result!!.documents[documentsdata.size - 1]
                    database = DatabaseService(context)
                    allExpenses = database.getAllExpense()
                    var success: Int = -1
                    if (allExpenses != expenseArrayList) {
                        for (i in expenseArrayList) {
                            success = database.insertThreads(
                                ExpenseData(
                                    0,
                                    i.trainer_name,
                                    i.expense_date,
                                    i.item_name,
                                    i.item_money
                                )
                            )
                        }
                    } else {
                        Log.d("Image Local Same", "Storage")
                    }
                    if (success == 1) {
                        Log.d("Image Local", "Storage")
                    } else {
                        Log.d("Image Local", "Storage Fail")
                    }
                }

            }

        }
    }

    fun getDataFromLocal(expenseList: MutableLiveData<List<Expense>>) {

        database = DatabaseService(context)
        allExpenses = database.getAllExpense()
        expenseList.postValue(allExpenses)

    }

    companion object {
        const val DATE_KEY = "expense_date"
        const val TRAINER_NAME_KEY = "trainer_name"
        const val PURCHASE_ITEM_KEY = "expense_item"
        const val PURCHASE_EXPENSE_KEY = "expense_money"
    }
}