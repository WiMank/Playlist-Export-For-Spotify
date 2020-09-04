package com.wimank.pbfs.ui.adapter

class ShimmerStub : PlayListItemType {
    override fun getType() = PlayListItemType.SHIMMER
}

class ErrorStub : PlayListItemType {
    override fun getType() = PlayListItemType.ERROR
}
