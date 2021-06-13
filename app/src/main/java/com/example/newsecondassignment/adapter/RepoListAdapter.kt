package com.example.newsecondassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsecondassignment.RepoInfo
import com.example.newsecondassignment.databinding.ItemRepoBinding

class RepoListAdapter:RecyclerView.Adapter<RepoListAdapter.RepoViewHolder>() {

    val repoList = mutableListOf<RepoInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoViewHolder(binding)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    class RepoViewHolder(
        private val binding:ItemRepoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(repoInfo: RepoInfo) {
            binding.repoName.text = repoInfo.userRepoName
            binding.repoDescription.text = repoInfo.userRepoDescription
            binding.repoLanguage.text = repoInfo.userRepoLanguage

        }
    }

}