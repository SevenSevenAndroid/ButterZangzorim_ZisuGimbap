package com.example.secondassignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.secondassignment.data.FollowingUserInfo
import com.example.secondassignment.databinding.FragmentFollowingListBinding

class FollowingListFragment:Fragment() {

    private var _binding: FragmentFollowingListBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private lateinit var followingListAdapter: FollowingListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFollowingListBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        followingListAdapter = FollowingListAdapter()
        binding.userList.adapter = followingListAdapter


        followingListAdapter.userList.addAll(
            listOf<FollowingUserInfo>(
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 나중에 채워요",
                    userName = "cjsjizzu"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 나중에 채워요",
                    userName = "qwer"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 나중에 채워요",
                    userName = "asdf"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 나중에 채워요",
                    userName = "zxcv"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈칸! 나중에 채워요",
                    userName = "1234"
                )
            )
        )
        followingListAdapter.notifyDataSetChanged()
    }
}