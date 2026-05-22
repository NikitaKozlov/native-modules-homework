package com.mobile

import android.util.Log
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class CustomButtonViewManager : SimpleViewManager<CustomButton>() {
    override fun getName(): String {
        return "CustomButton"
    }

    override fun createViewInstance(reactContext: ThemedReactContext): CustomButton {
        return CustomButton(reactContext)
    }

    override fun getExportedCustomBubblingEventTypeConstants(): Map<String, Any>? {
        return mapOf(
            "onPress" to mapOf(
                "phasedRegistrationNames" to mapOf(
                    "bubbled" to "onPress"
                )
            )
        )
    }

    @ReactProp(name = "disabled", defaultBoolean = false)
    fun setDisabled(button: CustomButton, disabled: Boolean) {
        button.disabled = disabled
    }

    @ReactProp(name = "title")
    fun setTitle(button: CustomButton, text: String?) {
        button.text = text
    }
}