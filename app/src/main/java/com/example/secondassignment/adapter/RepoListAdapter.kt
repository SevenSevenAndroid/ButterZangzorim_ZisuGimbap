package com.example.secondassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secondassignment.data.RepoInfo
import com.example.secondassignment.databinding.ItemRepoBinding

class RepoListAdapter:RecyclerView.Adapter<RepoListAdapter.RepoInfoViewHolder>() {

    val repoList = mutableListOf<RepoInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoInfoViewHolder {
        val binding = ItemRepoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RepoInfoViewHolder(binding)
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepoInfoViewHolder, position: Int) {
        holder.onBind(repoList[position])
    }

    class RepoInfoViewHolder(
        private val binding:ItemRepoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun onBind(repoInfo: RepoInfo) {
            binding.textViewRepoName.text = repoInfo.repoName
            binding.textViewRepoDesc.text = repoInfo.repoDesc
            binding.textViewOLanguage.text = repoInfo.repoLanguage
        }
    }
}