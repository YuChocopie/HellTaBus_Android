package com.mashup.healthyup.bridge

import android.os.Handler
import android.util.Log
import android.webkit.JavascriptInterface
import com.google.gson.JsonObject
import org.json.JSONObject

class JavaScriptInterface(private val webView: HealthyUpWebView?) : JavaScriptInterfaceCallback {

    private val TAG = javaClass.simpleName
    private val handler = Handler()

    override fun onJavaScriptResponse(eventType: String, eventData: JSONObject) {
        val extra = eventData.toString()
        val loadUrlStr = "javascript:healthyup.event(\'$eventType\',\'$extra\')"
        Log.d(TAG, "loadUrlMsg: $loadUrlStr")
        try {
            webView?.loadUrl(loadUrlStr) ?: Log.d(TAG, "WebView is destroyed!")
        } catch (e: Exception) {
            Log.e(TAG, "Uncaught ReferenceError:: " + e.message)
        }
    }

    @JavascriptInterface
    fun call(funcName: String, options: String, transactionId: String) {
        handler.post {
            Log.d(
                TAG,
                "[Web Call] API full name: $funcName / options: $options / transactionId: $transactionId"
            )
        }
    }

    override fun onDestroy() {}

    private fun makeReturnMsg(
        resultCode: Int,
        resultMsg: String,
        extra: JsonObject,
        transactionId: String
    ): JsonObject? {
        var result = JsonObject()

        result.addProperty("result_cd", resultCode)
        result.addProperty("result_msg", resultMsg)
        result.add("extra", extra)
        result.addProperty("transactionId", transactionId)

        return result
    }
}