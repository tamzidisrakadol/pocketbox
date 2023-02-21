package com.example.pocket.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.pocket.Utils
import com.example.pocket.databinding.ItemLayoutBinding
import com.example.pocket.model.Post

class PostAdapter(private val postList:List<Post>,val context:Context,val onItemClick:IPostAdapter): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {
    private lateinit var itemLayoutBinding: ItemLayoutBinding


    class PostViewHolder(val itemLayoutBinding: ItemLayoutBinding):RecyclerView.ViewHolder(itemLayoutBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        itemLayoutBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(itemLayoutBinding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postModel = postList[position]
        holder.itemLayoutBinding.postTitle.text = postModel.text
        holder.itemLayoutBinding.userName.text = postModel.createdBy.displayName
        Glide.with(holder.itemLayoutBinding.userImage.context).load(postModel.createdBy.imgUrl).circleCrop().into(itemLayoutBinding.userImage)
        holder.itemLayoutBinding.createdAt.text = Utils.getTimeAgo(postModel.createdAt)
        holder.itemLayoutBinding.likeCount.text = postModel.likedBy.size.toString()

    }
}

interface IPostAdapter{
    fun onClick(postId:String)
}