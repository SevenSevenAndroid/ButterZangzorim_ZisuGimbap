package com.example.newsecondassignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsecondassignment.databinding.FragmentRepoListBinding

class RepoListFragment : Fragment() {

    private var _binding:FragmentRepoListBinding? = null
    private val binding get() = _binding?:error("View를 참조하기 위해 binding이 초기화되지 않았습니다.")
    private lateinit var repoListAdapter: RepoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRepoListBinding.inflate(
            inflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        repoListAdapter = RepoListAdapter()
//
//        binding.repoList.adapter = repoListAdapter
//
//        repoListAdapter.repoList.addAll(
//            listOf<RepoInfo>(
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                ),
//                RepoInfo(
//                    userRepoName = "qqqqq",
//                    userRepoDescription = "awesome",
//                    userRepoLanguage = "kt"
//                )
//            )
//        )
//        repoListAdapter.notifyDataSetChanged()
    }
}