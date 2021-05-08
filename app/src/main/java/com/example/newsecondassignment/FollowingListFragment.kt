package com.example.newsecondassignment

import android.os.Binder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsecondassignment.databinding.FragmentFollowingListBinding


class FollowingListFragment : Fragment() {

    // _binding -> 뷰가 만들어질 때 초기화하고, 뷰가 죽으면 참조를 삭제해줌
    private var _binding: FragmentFollowingListBinding? = null
    private val binding get() = _binding ?: error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private lateinit var followingListAdapter: FollowingListAdapter

    override fun onCreateView( // fragment의 뷰를 그리는 시점
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "fragment - onCreateView")

        // 1. ViewBinding을 이용해 binding 객체 만들어주기 -> 2. UserInfoActivity에 FollowingListFragment 보여주기
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

        // 사용할 어댑터의 초기 값 넣어주기
        followingListAdapter = FollowingListAdapter()

        // RecyclerView에 어댑터를 우리가 만든 어댑터로 만들어주기
        binding.userList.adapter = followingListAdapter

        followingListAdapter.userList.addAll(
            listOf<FollowingUserInfo>(
                FollowingUserInfo(
                    userImage = "지금은 빈 칸",
                    userName = "cjsjizzu"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈 칸",
                    userName = "cjsjizzu"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈 칸",
                    userName = "cjsjizzu"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈 칸",
                    userName = "cjsjizzu"
                ),
                FollowingUserInfo(
                    userImage = "지금은 빈 칸",
                    userName = "cjsjizzu"
                )
            )
        )
        followingListAdapter.notifyDataSetChanged()
    }
}