package com.wimank.pbfs.rest

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationBearerInterceptor(private val token: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", "Bearer $token")
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }
}
