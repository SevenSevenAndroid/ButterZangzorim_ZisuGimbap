package com.example.newsecondassignment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsecondassignment.FollowingUserInfo
import com.example.newsecondassignment.databinding.ItemFollowUserBinding

// 1. Adapter를 만들기 위해서 RecyclerView의 Adapter를 상속 -> <> 안에는  데이터를 어떤 뷰로 바꿀지에 해당하는 뷰홀더가 들어감 -> 일단 비워둠
// 3. <> 안에 만든 뷰홀더 넣어주기
// 4. FollowingListAdapter -> Implement Methods
class FollowingListAdapter : RecyclerView.Adapter<FollowingListAdapter.FollowingUserViewHolder>() {

    // 5. 아까 만든 데이터 클래스 타입의 리스트 생성
    val userList = mutableListOf<FollowingUserInfo>()

    // Adapter는 아이템마다 ViewHolder를 만드는 방법 정의 필요
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowingUserViewHolder {

        // 6. 우리가 만들 뷰홀더에서 뷰를 참조하고 관리하기 위해서 ViewBinding 객체를 만들어주는 부분
        val binding = ItemFollowUserBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return FollowingUserViewHolder(binding)
    }

    // Adapter는 전체 아이템의 수를 알아야 함
    // 7. 모든 리스트는 해당 리스트의 전체 아이템 수를 size라는 친구로 반환
    override fun getItemCount(): Int = userList.size

    // Adapter는 ViewHolder에 Data를 전달하는 방법을 정의해야 함
    override fun onBindViewHolder(holder: FollowingUserViewHolder, position: Int) {
        // 8. Data를 전달하며 뷰홀더가 알아서 data를 뷰에 묶어줄 수 있도록 시키기 -> 뷰홀더에게 리스트에서 지금 보여줘야 할 부분에 대한 위치의 데이터를 보내줌
        holder.onBind(userList[position])
    }

    // 2. 뷰홀더 만들기
    class FollowingUserViewHolder(
        private val binding: ItemFollowUserBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        // 9. 위의 onBind() 정의 -> 뷰홀더가 받은 데이터를 어떻게 묶어줄지 정의
        fun onBind(followingUserInfo: FollowingUserInfo) {
            binding.followUserName.text = followingUserInfo.userName
        }
    }
}