package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.rest.response.NetworkUser
import com.wimank.pbfs.room.entity.UserEntity
import com.wimank.pbfs.util.EMPTY_STRING
import com.wimank.pbfs.util.ONE_HOUR
import javax.inject.Inject

class UserEntityMapper @Inject constructor() : Mapper<NetworkUser, UserEntity> {
    override fun map(input: NetworkUser): UserEntity {
        return UserEntity(
            name = input.displayName ?: EMPTY_STRING,
            avatar = if (input.images.isNullOrEmpty()) EMPTY_STRING else input.images[0].url
                ?: EMPTY_STRING,
            updateTime = System.currentTimeMillis() + ONE_HOUR
        )
    }
}

class UserMapper @Inject constructor() : Mapper<UserEntity, User> {
    override fun map(input: UserEntity): User {
        return User(
            name = input.name,
            avatar = input.avatar
        )
    }
}
