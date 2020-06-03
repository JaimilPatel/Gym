package com.jp.gym.ui.dashboard.member.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentMemberListBinding
import com.jp.gym.ui.dashboard.member.adapter.MemberAdapter
import com.jp.gym.ui.dashboard.member.viewmodel.MemberListViewModel
import kotlinx.android.synthetic.main.fragment_member_list.*

class MemberListFragment : GymAppFragment() {

    lateinit var mFragmentBinding: FragmentMemberListBinding
    private lateinit var memberListAdapter: MemberAdapter
    private val mViewModel by viewModels<MemberListViewModel>()

    override fun layoutResource(): Int {
        return R.layout.fragment_member_list
    }

    override fun preDataBinding(arguments: Bundle?) {

    }

    override fun postDataBinding(binding: ViewDataBinding): ViewDataBinding {
        mFragmentBinding = binding as FragmentMemberListBinding
        mFragmentBinding.viewModel = mViewModel
        mFragmentBinding.lifecycleOwner = this
        return mFragmentBinding
    }

    override fun initializeComponents(view: View?) {
        initUI()
        mViewModel.memberListLiveData.observe(this, Observer {
            it?.let {
                if (it.size > 0) {
                    memberListAdapter.setMemberList(it)
                    rvMember?.adapter = memberListAdapter
                }
            }
        })
    }

    private fun initUI() {
        memberListAdapter = MemberAdapter()
        rvMember.adapter = memberListAdapter

        val animator = rvMember.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

}