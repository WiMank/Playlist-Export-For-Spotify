package com.wimank.pbfs.util

class AccessTokenException : Exception() {
    override val message: String? = "Access token is required for spotify api requests"
}
