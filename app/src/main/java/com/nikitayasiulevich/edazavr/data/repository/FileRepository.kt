package com.nikitayasiulevich.edazavr.data.repository

import android.app.Application
import android.net.Uri
import com.nikitayasiulevich.edazavr.data.FileReader
import com.nikitayasiulevich.edazavr.data.remote.ApiService
import com.nikitayasiulevich.edazavr.data.remote.HttpRouts
import com.nikitayasiulevich.edazavr.data.remote.response.ImageUploadResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.onUpload
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class FileRepository(
    private val application: Application,
    private val fileReader: FileReader
) {
    private val apiService = ApiService.create(application)

    suspend fun uploadFile(contentUri: Uri): ImageUploadResponse? {  // Changed return type
        val info = fileReader.uriToFileInfo(contentUri)
        return apiService.uploadImage(info) { _, _ ->
            // We're no longer emitting progress updates here,
            // as we're using a simple loading indicator.
        }
    }
}

data class ProgressUpdate(
    val bytesSent: Long,
    val totalBytes: Long
)