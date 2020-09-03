package com.wimank.pbfs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wimank.pbfs.databinding.PlaylistItemBinding
import com.wimank.pbfs.domain.model.Playlist

class PlaylistAdapter : RecyclerView.Adapter<PlaylistViewHolder>() {

    private val dataList = mutableListOf<Playlist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            PlaylistItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount() = dataList.size

    fun setData(newData: List<Playlist>) {
        DiffUtil.calculateDiff(PlaylistDiffUtil(dataList, newData)).run {
            dataList.clear()
            dataList.addAll(newData)
            dispatchUpdatesTo(this@PlaylistAdapter)
        }
    }
}
