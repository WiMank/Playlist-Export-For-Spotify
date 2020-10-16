package com.wimank.pbfs.utils

object FileUtils {
    fun readTestResourceFile(fileName: String): String {
        val fileInputStream = javaClass.classLoader?.getResourceAsStream("json_response/$fileName")
        return fileInputStream?.bufferedReader()?.readText() ?: ""
    }
}
