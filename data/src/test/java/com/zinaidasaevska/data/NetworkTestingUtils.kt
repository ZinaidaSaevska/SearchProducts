package com.zinaidasaevska.data

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import java.nio.charset.StandardCharsets

internal fun MockWebServer.enqueueResponse(fileName: String, code: Int) {
    val inputStream = javaClass.classLoader?.getResourceAsStream(fileName)

    val source = inputStream?.let { inputStream.source().buffer() }
    source?.let {
        val body = source.readString(StandardCharsets.UTF_8)
        enqueue(
                MockResponse()
                        .setResponseCode(code)
                        .setBody(body)
        )
    }
}