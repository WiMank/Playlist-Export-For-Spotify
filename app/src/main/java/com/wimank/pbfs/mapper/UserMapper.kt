package com.wimank.pbfs.mapper

import com.wimank.pbfs.domain.User
import com.wimank.pbfs.rest.request.NetworkUser

class UserMapper : Mapper<NetworkUser, User> {
    override fun map(input: NetworkUser): User {
        TODO("UserMapper map not implemented")
    }
}
