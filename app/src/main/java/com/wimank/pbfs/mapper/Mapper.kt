package com.wimank.pbfs.mapper

/**
 * This is where we map DTOs into Domain Models.
 * */
interface Mapper<in I, out O> {

    fun map(input: I): O

}
