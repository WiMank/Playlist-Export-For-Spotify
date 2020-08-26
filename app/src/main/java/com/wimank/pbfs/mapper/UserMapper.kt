package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.model.User
import com.wimank.pbfs.rest.response.NetworkUser

class UserMapper : Mapper<NetworkUser, User> {
    override fun map(input: NetworkUser): User {
        TODO("UserMapper map not implemented")
    }
}
