package com.wimank.pbfs.work

import android.content.Context
import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.domain.model.Track
import com.wimank.pbfs.util.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class HtmlBuilder(
    private val playlists: Playlist,
    private val tracks: List<Track>,
    private val context: Context
) {

    fun createFile() {
        val text = buildString {
            appendHTML().html {
                head {
                    title(TITLE_FOR_PAGE)
                    style {
                        unsafe {
                            raw(TABLE_STYLE)
                            raw(TD_TH_STYLE)
                        }
                    }
                }
                body {
                    h1 { +playlists.name }
                    table {
                        tr {
                            th { +ARTISTS_COLUMN }
                            th { +TRACK_NAME_COLUMN }
                        }
                        tracks.forEach {
                            tr {
                                td { +it.artists }
                                td { +it.name }
                            }
                        }
                    }
                }
            }
        }
        writeFile(text)
    }

    private fun writeFile(text: String) {
        File(File(context.filesDir, APP_FOLDER), playlists.name + HTML_EXT).run {
            BufferedWriter(FileWriter(this)).use { it.write(text) }
        }
    }
}
