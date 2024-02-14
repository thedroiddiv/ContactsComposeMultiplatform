package com.plcoding.contactscomposemultiplatform.core.data

actual class ImageStorage {
    actual suspend fun saveImage(bytes: ByteArray): String {
        TODO("Not yet implemented")
    }

    actual suspend fun getImage(fileName: String): ByteArray? {
        TODO("Not yet implemented")
    }

    actual suspend fun deleteImage(fileName: String) {
    }
}