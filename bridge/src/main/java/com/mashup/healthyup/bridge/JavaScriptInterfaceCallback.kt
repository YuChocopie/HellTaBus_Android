package com.mashup.healthyup.bridge

import org.json.JSONObject

interface JavaScriptInterfaceCallback {
    fun onJavaScriptResponse(eventType: String, eventData: JSONObject)
    fun onDestroy()
}