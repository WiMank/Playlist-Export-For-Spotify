package com.wimank.pbfs.di

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.wimank.pbfs.CLIENT_ID
import com.wimank.pbfs.REDIRECT_URI
import com.wimank.pbfs.SPOTIFY_SCOPES
import com.wimank.pbfs.util.AUTHORIZATION_ENDPOINT
import com.wimank.pbfs.util.TOKEN_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class AuthModule {

    companion object {
        const val AUTH_INTENT = "AuthIntent"
    }

    @Provides
    @ActivityScoped
    fun authService(@ActivityContext context: Context): AuthorizationService {
        return AuthorizationService(context)
    }

    @Provides
    @ActivityScoped
    @Named(AUTH_INTENT)
    fun buildAuthIntent(
        authorizationService: AuthorizationService,
        authorizationRequestBuilder: AuthorizationRequest.Builder
    ): Intent {
        return authorizationService.getAuthorizationRequestIntent(
            authorizationRequestBuilder.setScopes(SPOTIFY_SCOPES.asIterable()).build()
        )
    }

    /**
     * Create AuthRequest.
     */
    @Provides
    @ActivityScoped
    fun prepareAuthRequestBuilder(serviceConfiguration: AuthorizationServiceConfiguration): AuthorizationRequest.Builder {
        return AuthorizationRequest.Builder(
            serviceConfiguration,  // the authorization service configuration
            CLIENT_ID,  // the client ID, typically pre-registered and static
            ResponseTypeValues.CODE,  // the response_type value: we want a code
            Uri.parse(REDIRECT_URI) // the redirect URI to which the auth response is sent
        )
    }

    /**
     * Create Service Configuration.
     * Set authorization endpoint and token endpoint.
     */
    @Provides
    @ActivityScoped
    fun prepareServiceConfig(): AuthorizationServiceConfiguration {
        return AuthorizationServiceConfiguration(
            Uri.parse(AUTHORIZATION_ENDPOINT),  // authorization endpoint
            Uri.parse(TOKEN_ENDPOINT) // token endpoint
        )
    }
}
