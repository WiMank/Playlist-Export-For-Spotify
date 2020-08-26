package com.wimank.pbfs.mapper

interface Mapper<in I, out O> {

    fun map(input: I): O

}
