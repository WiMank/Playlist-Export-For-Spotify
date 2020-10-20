package com.wimank.pbfs.utils

import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.rest.response.NetworkUser
import com.wimank.pbfs.room.entity.UserEntity

object MockUser {

    fun getNetworkUser() = NetworkUser(
        country = "SE",
        displayName = "JM Wizzler",
        email = "email@example.com",
        externalUrls = NetworkUser.ExternalUrls(spotify = "https://open.spotify.com/user/wizzler"),
        followers = NetworkUser.Followers(null, 3829),
        href = "https://api.spotify.com/v1/users/wizzler",
        id = "wizzler",
        images = listOf(
            NetworkUser.Image(
                height = null,
                url = "https://fbcdn-profile-a.akamaihd.net/hprofile-ak-frc3/t1.0-1/1970403_10152215092574354_1798272330_n.jpg",
                width = null
            )
        ),
        product = "premium",
        type = "user",
        uri = "spotify:user:wizzler"
    )

    fun getUserEntity() = UserEntity(name = "n", avatar = "a", updateTime = 100)

    fun getUser() = User(name = "n", avatar = "a")

}
