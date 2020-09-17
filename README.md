<h1 align="center">Playlist Export For Spotify</h1>

<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/WiMank/Playlist-Export-For-Spotify/actions"><img alt="Build Status" src="https://github.com/WiMank/Playlist-Export-For-Spotify/workflows/Android%20CI/badge.svg"/></a> 
  <a href="https://app.codacy.com/manual/WiMank/Playlist-Export-For-Spotify?utm_source=github.com&utm_medium=referral&utm_content=WiMank/Playlist-Export-For-Spotify&utm_campaign=Badge_Grade_Dashboard"><img alt="Codacy" src="https://api.codacy.com/project/badge/Grade/3da0b2b4662e4ec08722b73088a7d1fe"/></a> 

<p align="center">  
Playlist Export For Spotify is a small demo application based on modern Android application tech-stacks and MVVM architecture.
</p>

<p align="center">
<img src="/previews/scr.png"/>
</p>

## Download
Go to the [Releases](https://github.com/WiMank/Playlist-Export-For-Spotify/releases) to download the latest APK.  

## Tech stack  
* [Room](https://developer.android.com/topic/libraries/architecture/room) - persistence library provides an abstraction layer over SQLite;  
* [Retrofit2](https://github.com/square/retrofit) - type-safe HTTP client for Android and Java;  
* [Glide](https://github.com/bumptech/glide) - fast and efficient open source media management and image loading framework;  
* [Moshi](https://github.com/square/moshi/) - modern JSON library for Android and Java;  
* [Dagger Hilt](https://dagger.dev/hilt/) - provides a standard way to incorporate Dagger dependency injection into an Android application;  
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - designed to store and manage UI-related data in a lifecycle conscious way;  
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - observable data holder class;  
* [Navigation](https://developer.android.com/guide/navigation) - navigation refers to the interactions that allow users to navigate across, into, and back out from the different pieces of content within your app;  
* [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - asynchronous or non-blocking programming is the new reality;  
* [AppAuth](https://github.com/openid/AppAuth-Android) - client SDK for communicating with OAuth 2.0 and OpenID Connect providers;  
* [Shimmer](https://github.com/facebook/shimmer-android) - android library that provides an easy way to add a shimmer effect to any view in your Android app;  
* [Work Manager](https://developer.android.com/topic/libraries/architecture/workmanager) - API that makes it easy to schedule deferrable, asynchronous tasks;  
* [KotlinxHtml](https://github.com/Kotlin/kotlinx.html) - provides DSL to build HTML;  
* [Material Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView;  
* [ZtZip](https://github.com/zeroturnaround/zt-zip) - library for working with zip archives;  
* [Timber](https://github.com/JakeWharton/timber) - This is a logger with a small, extensible API which provides utility on top of Android's normal Log class.  
 
## Architecture
Playlist Export For Spotify is based on MVVM architecture.

![architecture](/previews/final-architecture.png)  

## API
Playlist Export For Spotify uses [Spotify API](https://developer.spotify.com/documentation/web-api/) to make requests.  
