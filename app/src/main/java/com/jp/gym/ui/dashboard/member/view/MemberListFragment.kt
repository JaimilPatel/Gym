package com.jp.gym.ui.dashboard.member.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.SimpleItemAnimator
import com.jp.gym.R
import com.jp.gym.base.GymAppFragment
import com.jp.gym.databinding.FragmentMemberListBinding
import com.jp.gym.ui.dashboard.member.adapter.MemberAdapter
import com.jp.gym.ui.dashboard.member.viewmodel.MemberListViewModel
import kotlinx.android.synthetic.main.fragment_expense_list.*
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
            if(it!=null && it.size>0){
                memberListAdapter.setMemberList(it)
                rvMember?.adapter = memberListAdapter
                rvProgressMember.visibility = View.GONE
                tvNoMember.visibility= View.GONE
            }else{
                rvProgressMember.visibility = View.GONE
                tvNoMember.visibility= View.VISIBLE
            }
        })
        mViewModel.navigateTo.observe(this, Observer {
            it?.let {
                if(it){
                    activity?.findNavController(R.id.navDashboardHostFragment)?.navigate(R.id.addMember)
                }
            }
        })
    }

    private fun initUI() {
        memberListAdapter = MemberAdapter(mViewModel)
        rvMember.adapter = memberListAdapter

        val animator = rvMember.itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = false
        }
    }

}