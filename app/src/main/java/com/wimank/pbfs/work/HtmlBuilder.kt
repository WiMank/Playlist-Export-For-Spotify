package com.wimank.pbfs.work

import com.wimank.pbfs.domain.model.Playlist
import com.wimank.pbfs.domain.model.Track
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import timber.log.Timber

class HtmlBuilder(private val playlists: Playlist, private val tracks: List<Track>) {

    fun createFile() {
        val text = buildString {
            appendHTML().html {
                head {
                    title("Playlists")
                    style {
                        unsafe {
                            raw(
                                """
                                table {
                                  font-family: arial, sans-serif;
                                  border-collapse: collapse;
                                  width: 100%;	
                                }
                            """
                            )

                            raw(
                                """
                                td, th {
                                    border: 1px solid #dddddd;
                                    text-align: left;
                                    padding: 8px;
                                }
                            """
                            )
                        }
                    }
                }

                body {
                    h1 { +playlists.name }

                    table {
                        tr {
                            th { +"Artist" }
                            th { +"Track Name" }
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

        Timber.d("HTML $text")

    }
}
