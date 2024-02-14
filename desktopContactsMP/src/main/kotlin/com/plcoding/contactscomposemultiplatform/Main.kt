package com.plcoding.contactscomposemultiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.plcoding.contactscomposemultiplatform.core.presentation.ImagePickerFactory
import com.plcoding.contactscomposemultiplatform.di.AppModule

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "ContactsComposeMultiplatform") {
        App(
            darkTheme = true,
            dynamicColor = false,
            appModule = AppModule(),
            imagePicker = ImagePickerFactory().createPicker()
        )
    }
}
