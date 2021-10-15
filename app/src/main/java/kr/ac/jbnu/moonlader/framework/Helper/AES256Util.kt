package kr.ac.jbnu.moonlader.framework.Helper

import android.util.Base64
import java.lang.Exception
import java.security.spec.AlgorithmParameterSpec
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

open class AES256Util {
    private var SECRET_KEY = "01234567890123450123456789012345"
    private var IV = SECRET_KEY.substring(0, 16)

    fun aesEncode(str: String): String? {
        try {
            val textBytes = str.toByteArray(charset("UTF-8"))
            val ivSpec: AlgorithmParameterSpec = IvParameterSpec(IV.encodeToByteArray())
            val newKey = SecretKeySpec(SECRET_KEY.toByteArray(charset("UTF-8")), "AES")
            var cipher: Cipher? = null
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.ENCRYPT_MODE, newKey, ivSpec)
            return Base64.encodeToString(cipher.doFinal(textBytes), Base64.DEFAULT)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return str
    }

    fun aesDecode(str: String?): String? {
        try {
            val textBytes = Base64.decode(str, Base64.DEFAULT)
            val ivSpec: AlgorithmParameterSpec = IvParameterSpec(IV.encodeToByteArray())
            val newKey = SecretKeySpec(SECRET_KEY.toByteArray(charset("UTF-8")), "AES")
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, newKey, ivSpec)

            return String(cipher.doFinal(textBytes))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return str
    }
}