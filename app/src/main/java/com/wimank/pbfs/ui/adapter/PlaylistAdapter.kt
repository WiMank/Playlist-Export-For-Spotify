package com.wimank.pbfs.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.wimank.pbfs.databinding.ErrorItemBinding
import com.wimank.pbfs.databinding.PlaylistItemBinding
import com.wimank.pbfs.databinding.ShimmerProgressItemBinding
import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.ui.adapter.PlayListItemType.Companion.PLAYLIST
import com.wimank.pbfs.ui.adapter.PlayListItemType.Companion.SHIMMER

class PlaylistAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dataList = mutableListOf<PlayListItemType>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {

            SHIMMER -> {
                ShimmerViewHolder(
                    ShimmerProgressItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            PLAYLIST -> {
                PlaylistViewHolder(
                    PlaylistItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }

            else -> {
                ErrorViewHolder(
                    ErrorItemBinding
                        .inflate(LayoutInflater.from(parent.context), parent, false)
                )
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].getType()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (dataList[position].getType()) {
            PLAYLIST -> {
                with(holder as PlaylistViewHolder) {
                    bind(dataList[position] as Playlist)
                }
            }
        }
    }

    override fun getItemCount() = dataList.size

    fun setData(newData: List<PlayListItemType>) {
        DiffUtil.calculateDiff(PlaylistDiffUtil(dataList, newData)).run {
            dataList.clear()
            dataList.addAll(newData)
            dispatchUpdatesTo(this@PlaylistAdapter)
        }
    }

    fun startLoading(): List<ShimmerStub> {
        var i = 0
        val list = mutableListOf<ShimmerStub>()
        while (i < 15) {
            list.add(ShimmerStub())
            i++
        }
        return list
    }

    fun showError(): List<ErrorStub> {
        var i = 0
        val list = mutableListOf<ErrorStub>()
        while (i < 15) {
            list.add(ErrorStub())
            i++
        }
        return list
    }

}
