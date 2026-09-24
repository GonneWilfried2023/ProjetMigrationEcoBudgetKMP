package com.example.utils   // ⚠ la diapo 76 écrit "com.example.UTILS" : c'est une coquille

import platform.Foundation.NSUUID

actual fun generateUUID(): String {
    return NSUUID().UUIDString()
}