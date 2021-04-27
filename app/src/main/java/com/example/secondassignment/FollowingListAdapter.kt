package com.example.secondassignment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.secondassignment.data.FollowingUserInfo


// Adapter를 만들기 위해서는 RecyclerView의 Adapter 상속 필요
// 해당 Adapter <> 안에는 어떤 뷰로 바꿀지에 해당하는 뷰홀더가 들어간다
class FollowingListAdapter:RecyclerView.Adapter<FollowingListAdapter.FollowingUserViewHolder>() {

    // Adapter는 ViewHolder로 변경할 Data를 가지고 있어야 한다
    val userList = mutableListOf<FollowingUserInfo>()

    // Adapter는 아이템마다 ViewHolder를 만드는 방법을 가지고 있어야 한다
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingUserViewHolder {
        val binding = ItemFollowUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingUserViewHolder(binding) // 생성된 뷰 객체를 기반으로 뷰홀더에 참조할 뷰 객체를 넘겨줌
    }

    // Adapter는 전체 아이템의 수를 알아야 한다
    override fun getItemCount(): Int = userList.size // 모든 리스트는 해당 리스트의 전체 아이템 수를 size라는 친구로 반환

    // Adapter는 ViewHolder에 Data를 전달하는 방법을 정의해야 한다
    // 뷰 홀더가 객체에게 "내가 준 데이터를 뷰에 배치해줘!"라고 메시지를 던진다 -> 그리고 Data 전달
    override fun onBindViewHolder(holder: FollowingUserViewHolder, position: Int) {
        // "뷰홀더(holder)야, 내가 주는 데이터(userList[position])에 대해서 데이터를 뷰에 묶어줘(onBind)!"
        holder.onBind(userList[position]) // 뷰 홀더에게 리스트에서 지금 보여줘야 할 부분에 대한 위치의 데이터를 보내줌
    }

    class FollowingUserViewHolder(
        private val binding:ItemFollowUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(followingUserInfo: FollowingUserInfo) {
            binding.followUserName.text = followingUserInfo.userName // 뷰 홀더가 받은 데이터를 어떻게 묶어줘야 하는지 정의하는 부분
        }
    }
}