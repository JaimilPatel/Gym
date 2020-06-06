package com.jp.gym.ui.dashboard.member.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jp.gym.R
import com.jp.gym.databinding.ItemMemberBinding
import com.jp.gym.ui.dashboard.member.model.Member
import com.jp.gym.ui.dashboard.member.viewmodel.MemberListViewModel

class MemberAdapter(var viewModel: MemberListViewModel) :
    RecyclerView.Adapter<MemberAdapter.MemberViewHolder>() {

    private val memberList = ArrayList<Member>()

    class MemberViewHolder(val binding: ItemMemberBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setViewItem(resMember: Member, viewModel: MemberListViewModel) {
            binding.memberItemInfo = resMember
            binding.viewModel = viewModel
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MemberViewHolder {
        val memberListBinding: ItemMemberBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.item_member, parent, false)
        return MemberViewHolder(memberListBinding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: MemberViewHolder, position: Int) {
        holder.setViewItem(memberList[position], viewModel)
    }

    fun setMemberList(list: List<Member>) {
        memberList.clear()
        memberList.addAll(list)
        notifyDataSetChanged()
    }
}