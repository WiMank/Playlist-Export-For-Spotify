package com.wimank.pbfs.util

//Required for spotify api access
fun String.bearer(): String {
    return BEARER.plus(this)
}
