package com.mobile

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.uimanager.events.RCTEventEmitter

class CustomButton(context: Context) : AppCompatButton(context) {

    var disabled: Boolean = false
        set(value) {
            field = value
            isEnabled = !value
            setOnClickListener()
        }

    init {
        setOnClickListener()
    }

    private fun setOnClickListener() {
        if (isEnabled) {
            setOnClickListener { viewClicked: View? -> 
                (getContext() as ReactContext)
                    .getJSModule(RCTEventEmitter::class.java)
                    .receiveEvent(
                        id, 
                        "onPress", 
                        Arguments.createMap() 
                    )
            }
        }
        
    }
}