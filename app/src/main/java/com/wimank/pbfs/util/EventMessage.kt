package com.wimank.pbfs.util

/**
 * Events in the app.
 */
sealed class EventMessage
data class Timeout(val time: String) : EventMessage()
data class LoadComplete(val message: Int) : EventMessage()
data class LoadError(val message: Int) : EventMessage()
data class OfflineMode(val message: Int) : EventMessage()
data class EmptyList(val message: Int) : EventMessage()
data class Logout(val message: Int) : EventMessage()
