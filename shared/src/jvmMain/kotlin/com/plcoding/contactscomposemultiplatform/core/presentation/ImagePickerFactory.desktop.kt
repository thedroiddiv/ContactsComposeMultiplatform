package com.plcoding.contactscomposemultiplatform.core.presentation

import androidx.compose.runtime.Composable

actual class ImagePickerFactory {
    @Composable
    actual fun createPicker(): ImagePicker {
        return ImagePicker()
    }

}