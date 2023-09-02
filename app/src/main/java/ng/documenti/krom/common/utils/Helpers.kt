package ng.documenti.krom.common.utils

import android.content.Context
import android.net.Uri
import java.io.IOException

class AppUtils {
    companion object {
        @Throws(IOException::class)
        fun readBytes(context: Context, uri: Uri): ByteArray? =
            context.contentResolver.openInputStream(uri)?.use { it.buffered().readBytes() }
    }
}

