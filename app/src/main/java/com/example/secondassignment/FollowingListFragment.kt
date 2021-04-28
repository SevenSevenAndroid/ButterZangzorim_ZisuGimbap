package com.example.secondassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.secondassignment.data.FollowingUserInfo
import com.example.secondassignment.databinding.FragmentFollowingListBinding



class FollowingListFragment : Fragment() {

    private var _binding: FragmentFollowingListBinding? =null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private lateinit var followingListAdapter: FollowingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        followingListAdapter = FollowingListAdapter()

        binding.userList.adapter = followingListAdapter

        followingListAdapter.userList.addAll(
            listOf<FollowingUserInfo>(
                FollowingUserInfo(
                    userImage = "dd",
                    userName = "jisusu"
                ),
                FollowingUserInfo(
                    userImage = "dd",
                    userName = "Jisu"
                ),
                FollowingUserInfo(
                    userImage = "dd",
                    userName = "jisusjsusju"
                ),
                FollowingUserInfo(
                    userImage = "dd",
                    userName = "jisusus"
                )
            )
        )
        followingListAdapter.notifyDataSetChanged()
    }
}