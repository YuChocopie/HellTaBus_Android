package com.mashup.healthyup.bridge

import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient

class HealthyUpWebChromeClient : WebChromeClient() {
    private val TAG = javaClass.simpleName
    override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
        Log.i(
            TAG,
            "[JavaScript Msg] " + consoleMessage.message() + " -- from Line #" + consoleMessage.lineNumber() + " of " + consoleMessage.sourceId()
        )
        return true
    }

    override fun onPermissionRequest(request: PermissionRequest) {
        request.grant(request.getResources())
    }
}