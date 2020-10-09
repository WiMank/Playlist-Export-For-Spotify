package com.wimank.pbfs.di

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.wimank.pbfs.BuildConfig
import com.wimank.pbfs.util.AUTHORIZATION_ENDPOINT
import com.wimank.pbfs.util.SPOTIFY_SCOPES
import com.wimank.pbfs.util.TOKEN_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import javax.inject.Named

@Module
@InstallIn(FragmentComponent::class)
class AuthModule {

    companion object {
        const val AUTH_INTENT = "AuthIntent"
    }

    @Provides
    @FragmentScoped
    fun authService(@ActivityContext context: Context): AuthorizationService {
        return AuthorizationService(context)
    }

    @Provides
    @FragmentScoped
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
    @FragmentScoped
    fun prepareAuthRequestBuilder(serviceConfiguration: AuthorizationServiceConfiguration): AuthorizationRequest.Builder {
        return AuthorizationRequest.Builder(
            serviceConfiguration,  // the authorization service configuration
            BuildConfig.clientId,  // the client ID, typically pre-registered and static
            ResponseTypeValues.CODE,  // the response_type value: we want a code
            Uri.parse(BuildConfig.redirectUri) // the redirect URI to which the auth response is sent
        )
    }

    /**
     * Create Service Configuration.
     * Set authorization endpoint and token endpoint.
     */
    @Provides
    @FragmentScoped
    fun prepareServiceConfig(): AuthorizationServiceConfiguration {
        return AuthorizationServiceConfiguration(
            Uri.parse(AUTHORIZATION_ENDPOINT),  // authorization endpoint
            Uri.parse(TOKEN_ENDPOINT) // token endpoint
        )
    }
}
