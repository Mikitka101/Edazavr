package com.nikitayasiulevich.edazavr.presentation.createRestaurant

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikitayasiulevich.edazavr.data.remote.response.ImageUploadResponse
import com.nikitayasiulevich.edazavr.data.repository.FileRepository
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okio.FileNotFoundException

class CreateRestaurantViewModel(
    private val repository: FileRepository
) : ViewModel() {

    private val _state = MutableStateFlow<UploadState>(UploadState.Initial)
    val state = _state.asStateFlow()

    fun uploadFile(contentUri: Uri) {
        viewModelScope.launch {
            _state.value = UploadState.Loading
            try {
                val response = repository.uploadFile(contentUri) // Now receives ImageUploadResponse?
                if (response != null) {
                    _state.value = UploadState.Success(response)
                } else {
                    _state.value = UploadState.Error("Upload failed: Null response")
                }
            } catch (e: Exception) {
                _state.value = UploadState.Error(e.message)
            }
        }
    }
}


sealed class UploadState {
    object Initial : UploadState()
    object Loading : UploadState()
    data class Success(val response: ImageUploadResponse) : UploadState()
    data class Error(val message: String?) : UploadState()
}