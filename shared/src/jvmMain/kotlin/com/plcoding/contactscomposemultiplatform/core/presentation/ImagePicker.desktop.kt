package com.plcoding.contactscomposemultiplatform.core.presentation

import androidx.compose.runtime.Composable

actual class ImagePicker {
    @Composable
    actual fun registerPicker(onImagePicked: (ByteArray) -> Unit) {
    }

    actual fun pickImage() {
    }

}