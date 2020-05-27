package com.jp.gym.base

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class GymAppFragment : Fragment(), View.OnClickListener {


    private var lastClickTime: Long = 0

    protected var mBaseActivity: GymAppActivity? = null

    abstract fun layoutResource(): Int

    abstract fun preDataBinding(arguments: Bundle?)

    abstract fun postDataBinding(binding: ViewDataBinding): ViewDataBinding

    protected abstract fun initializeComponents(view: View?)

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mBaseActivity = context as GymAppActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        preDataBinding(arguments)
        var mViewDataBinding =
            DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutResource(), container, false)
        mViewDataBinding = postDataBinding(mViewDataBinding)
        return mViewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        initializeComponents(view)
    }

    override fun onClick(v: View?) {
        if (SystemClock.elapsedRealtime() - lastClickTime < 500) {
            return
        }
        lastClickTime = SystemClock.elapsedRealtime()
    }

    fun showToast(resource: String) {
        Toast.makeText(context, resource, Toast.LENGTH_SHORT).show()
    }
}