package com.wimank.pbfs.mapper

import com.google.common.truth.Truth.assertThat
import com.wimank.pbfs.util.ONE_HOUR
import com.wimank.pbfs.utils.MockUser.getNetworkUser
import com.wimank.pbfs.utils.MockUser.getUserEntity
import org.junit.jupiter.api.Test

class UserMapperTest {

    private val userMapper = UserMapper()
    private val userEntityMapper = UserEntityMapper()

    @org.junit.jupiter.api.Test
    fun userMapper() {
        val userEntity = getUserEntity()
        val mapResult = userMapper.map(userEntity)

        assertThat(userEntity.name).isEqualTo(mapResult.name)
        assertThat(userEntity.avatar).isEqualTo(mapResult.avatar)
    }

    @Test
    fun userEntityMapper() {
        val networkUser = getNetworkUser()
        val mapResult = userEntityMapper.map(networkUser)

        assertThat(networkUser.displayName).isEqualTo(mapResult.name)
        assertThat(networkUser.images!!.first().url).isEqualTo(mapResult.avatar)
        assertThat(mapResult.updateTime).isGreaterThan(ONE_HOUR)
    }
}
