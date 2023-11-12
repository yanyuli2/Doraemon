package com.cofbro.qian.friend.viewholder

import cn.leancloud.LCObject
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.cofbro.qian.databinding.ItemFriendListBinding
import com.cofbro.qian.friend.im.IMClientUtils
import com.cofbro.qian.utils.dp2px

class UserListContentViewHolder<T : LCObject>(private val binding: ItemFriendListBinding) :
    DefaultViewHolder<T>(binding) {
    override fun bind(position: Int, t: T?) {
        innerBind(t)
    }

    private fun bindAvatar(url: String?) {
        if (url.isNullOrEmpty()) return
        val options = RequestOptions().transform(
            CenterCrop(),
            RoundedCorners(dp2px(binding.root.context, 25))
        )
        Glide.with(binding.root)
            .load(url)
            .apply(options)
            .into(binding.ivAvatar)
    }

    private fun innerBind(t: T?) {
        if (t?.getString("ownerId") == IMClientUtils.getCntUser()?.objectId.toString()) {
            binding.tvUsername.text = t.getString("targetName") ?: ""
            val url = t.getString("targetAvatar") ?: ""
            bindAvatar(url)
        } else {
            binding.tvUsername.text = t?.getString("owner") ?: ""
            val url = t?.getString("ownerAvatar") ?: ""
            bindAvatar(url)
        }
    }
}