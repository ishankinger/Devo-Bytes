package com.example.devo_bytes.devByteViewer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.devo_bytes.databinding.DevbyteItemBinding
import com.example.devo_bytes.domain.Video

class DevByteVideoAdapter(private val clickListener : DevByteVideosListener,
                        private val clickListener2 : DevByteVideosListener2)
    : ListAdapter<Video,DevByteVideoAdapter.ViewHolder>(DevByteVideoDiffCallback()){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!,clickListener,clickListener2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: DevbyteItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bind(item: Video, clickListener: DevByteVideosListener, clickListener2: DevByteVideosListener2) {
            binding.video = item
            binding.onClickListener = clickListener
            binding.onClickListener2 = clickListener2
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = DevbyteItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }
    }
}

class DevByteVideoDiffCallback : DiffUtil.ItemCallback<Video>(){
    override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
        return oldItem == newItem
    }
}

class DevByteVideosListener(val clickListener : (video : Video)->Unit){
    fun click(video : Video) = clickListener(video)
}

class DevByteVideosListener2(val clickListener : (video : Video) -> Unit){
    fun click2(video : Video) = clickListener(video)
}