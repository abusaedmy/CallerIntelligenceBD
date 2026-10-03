package com.abusaed.callerintelligence

import android.telecom.Call
import android.telecom.CallScreeningService

class CallerScreeningService : CallScreeningService() {

    override fun onScreenCall(callDetails: Call.Details) {

        val phoneNumber = callDetails.handle?.schemeSpecificPart

        if (phoneNumber.isNullOrBlank()) {
            return
        }

        val response = CallResponse.Builder()
            .setDisallowCall(false)
            .setRejectCall(false)
            .setSilenceCall(false)
            .setSkipCallLog(false)
            .setSkipNotification(false)
            .build()

        respondToCall(callDetails, response)
    }
}
