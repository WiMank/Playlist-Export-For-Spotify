package com.wimank.pbfs.util

//Name for app database
const val DATABASE_NAME = "pbfs.db"

//The base address of Web API
const val BASE_SPOTIFY_URL = "https://api.spotify.com/v1/"

//The base address of refresh tokens
const val BASE_TOKEN_ENDPOINT_URL = "https://accounts.spotify.com"

//Authorization endpoint
const val AUTHORIZATION_ENDPOINT = "https://accounts.spotify.com/authorize"

//Token endpoint
const val TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token"

//Just empty string
const val EMPTY_STRING = ""

//Default session id
const val SESSION_ID = 0L

//Required for refresh token request.
const val REFRESH_TOKEN_GRANT_TYPE = "refresh_token"

//Bearer authentication header
const val BEARER = "Bearer "

//It's one minute :)
const val ONE_MINUTE = 60000L

const val HTML_EXT = ".html"

const val TABLE_STYLE =
    """table {font-family: arial, sans-serif;border-collapse: collapse;width: 100%;}"""

const val TD_TH_STYLE = """td, th {border: 1px solid #dddddd;ext-align: left;padding: 8px;}"""

const val ARTISTS_COLUMN = "Artist"
const val TRACK_NAME_COLUMN = "Track Name"
const val TITLE_FOR_PAGE = "Playlists"
const val APP_FOLDER = "Playlists"
const val ZIP_APP_FOLDER = "ZIP_Playlists"
const val ARCHIVE_NAME = "spotify_playlists.zip"
const val WORK_TAG = "export_work"
const val FILE_PROVIDER = "com.wimank.pbfs.fileprovider"
const val ZIP_MIME_TYPE = "application/zip"
const val ONE_HOUR = 3600000L
const val URL_REPOSITORY = "https://github.com/WiMank/Playlist-Backup-For-Spotify"
const val UNAUTHORIZED_CODE = 401
const val PLAYLISTS_LIMIT = 50
const val TRACKS_LIMIT = 50
const val START_OFFSET = 0
const val OK_HTTP_TAG = "OkHttp"
