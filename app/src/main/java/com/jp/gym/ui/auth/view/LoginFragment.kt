package com.jp.gym.ui.auth.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentLoginBinding
import com.jp.gym.ui.auth.viewmodel.LoginViewModel
import com.jp.gym.ui.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : GymAppFragment() {

    private lateinit var mFragmentBinding: FragmentLoginBinding
    private val mViewModel: LoginViewModel by viewModels()

    override fun layoutResource(): Int {
        return R.layout.fragment_login
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentLoginBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {
        btnSignIn.setOnClickListener {
            launchFirebaseAuthUi()
        }
    }


    private fun launchFirebaseAuthUi() {
        val authProvides = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(
                authProvides
            ).build(), SIGN_IN_RESULT_CODE
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            val response = IdpResponse.fromResultIntent(data)
            if (resultCode == Activity.RESULT_OK) {
                Log.i(
                    TAG,"${FirebaseAuth.getInstance().currentUser?.displayName}!"
                )
                val intent = Intent(context,DashboardActivity::class.java)
                startActivity(intent)
            } else {
                Log.i(TAG,"${response?.error?.errorCode}")
            }
        }
    }

    companion object {
        const val TAG = "LoginFragment"
        const val SIGN_IN_RESULT_CODE = 1001
    }
}