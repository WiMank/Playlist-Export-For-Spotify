package com.wimank.pbfs.utils

import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.rest.response.NetworkUser
import com.wimank.pbfs.room.entity.UserEntity

object MockUserRepository {

    fun getNetworkUser() = NetworkUser(
        country = "sp",
        displayName = "n",
        email = "@",
        externalUrls = NetworkUser.ExternalUrls(spotify = null),
        followers = null,
        href = "h",
        id = "0",
        images = listOf(),
        product = null,
        type = "t",
        uri = "u"
    )


    fun getUserEntity() = UserEntity(name = "n", avatar = "a", updateTime = 100)

    fun getUser() = User(name = "n", avatar = "a")

}
