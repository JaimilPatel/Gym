package com.jp.gym.ui.dashboard.member.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.jp.gym.ui.dashboard.member.model.Member

class MemberRepository(private var context: Context) {
    var firestore = FirebaseFirestore.getInstance()
    lateinit var query: Query
    lateinit var memberArrayList: ArrayList<Member>
    var memberListData: MutableLiveData<ArrayList<Member>> = MutableLiveData()
    lateinit var lastvisible: DocumentSnapshot
    lateinit var allMembers: List<Member>

    fun addMemberToDatabase(
        firstName: String,
        lastName: String,
        emailId: String,
        mobileNumber: String,
        batch: String,
        memberShipPlan: String,
        amount: String,
        paymentStatus: String

    ) {
        val addMemberCollection: HashMap<String, String> = hashMapOf(
            FIRST_NAME to firstName,
            LAST_NAME to lastName,
            EMAIL_ID to emailId,
            MOBILE_NUMBER to mobileNumber,
            BATCH to batch,
            MEMBERSHIP_PLAN to memberShipPlan,
            AMOUNT to amount,
            PAYMENT_STATUS to paymentStatus
        )

        firestore.collection("Members").add(addMemberCollection)
    }


    fun getMemberFromDatabase(memberList: MutableLiveData<ArrayList<Member>>) {


        query = FirebaseFirestore.getInstance().collection("Members").orderBy("memberShipPlan")

        query.get().addOnCompleteListener { task ->
            val documentsdata = task.result?.documents
            memberArrayList = ArrayList()
            for (i in 0..documentsdata?.size!! - 1) {
                val snap = documentsdata.get(i)
                val getFirstName: String = snap?.get("firstName").toString()
                val getLastName: String = snap?.get("lastName").toString()
                val getEmailId: String = snap?.get("emailId").toString()
                val getMobileNumber: String = snap?.get("mobileNumber").toString()
                val getBatch: String = snap?.get("batch").toString()
                val getMemberShipPlan: String = snap?.get("memberShipPlan").toString()
                val getAmount: String = snap?.get("amount").toString()
                val getPaymentStatus: String = snap?.get("paymentStatus").toString()
                val member: Member =
                    Member(
                        getFirstName,
                        getLastName,
                        getEmailId,
                        getMobileNumber,
                        getBatch,
                        getMemberShipPlan,
                        getAmount,
                        getPaymentStatus
                    )
                if (documentsdata.size > 0) {
                    memberArrayList.add(member)
                    memberListData.value = memberArrayList
                    memberList.postValue(memberArrayList)
                    lastvisible = task.result!!.documents[documentsdata?.size!! - 1]
                }

            }

        }
    }

    companion object {
        const val FIRST_NAME = "firstName"
        const val LAST_NAME = "lastName"
        const val EMAIL_ID = "emailId"
        const val MOBILE_NUMBER = "mobileNumber"
        const val BATCH = "batch"
        const val MEMBERSHIP_PLAN = "memberShipPlan"
        const val AMOUNT = "amount"
        const val PAYMENT_STATUS = "paymentStatus"
    }
}