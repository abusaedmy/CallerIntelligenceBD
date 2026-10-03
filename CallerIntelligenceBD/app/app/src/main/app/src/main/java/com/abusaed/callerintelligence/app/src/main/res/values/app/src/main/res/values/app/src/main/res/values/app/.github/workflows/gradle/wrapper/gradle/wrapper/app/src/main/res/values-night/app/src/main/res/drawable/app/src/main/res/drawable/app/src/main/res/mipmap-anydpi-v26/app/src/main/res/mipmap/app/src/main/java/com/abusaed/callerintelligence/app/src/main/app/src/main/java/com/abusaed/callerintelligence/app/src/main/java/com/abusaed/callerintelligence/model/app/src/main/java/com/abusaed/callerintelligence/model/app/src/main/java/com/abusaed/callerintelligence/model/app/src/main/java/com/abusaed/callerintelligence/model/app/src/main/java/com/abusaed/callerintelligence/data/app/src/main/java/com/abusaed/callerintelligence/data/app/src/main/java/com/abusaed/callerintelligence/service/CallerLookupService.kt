package com.abusaed.callerintelligence.service

import android.content.Context
import com.abusaed.callerintelligence.data.CallerRepository
import com.abusaed.callerintelligence.model.CallerProfile

class CallerLookupService(context: Context) {

    private val repository = CallerRepository(context.applicationContext)

    fun lookup(rawNumber: String?): CallerProfile? {
        if (rawNumber.isNullOrBlank()) {
            return null
        }

        return repository.findCaller(rawNumber)
    }
}
