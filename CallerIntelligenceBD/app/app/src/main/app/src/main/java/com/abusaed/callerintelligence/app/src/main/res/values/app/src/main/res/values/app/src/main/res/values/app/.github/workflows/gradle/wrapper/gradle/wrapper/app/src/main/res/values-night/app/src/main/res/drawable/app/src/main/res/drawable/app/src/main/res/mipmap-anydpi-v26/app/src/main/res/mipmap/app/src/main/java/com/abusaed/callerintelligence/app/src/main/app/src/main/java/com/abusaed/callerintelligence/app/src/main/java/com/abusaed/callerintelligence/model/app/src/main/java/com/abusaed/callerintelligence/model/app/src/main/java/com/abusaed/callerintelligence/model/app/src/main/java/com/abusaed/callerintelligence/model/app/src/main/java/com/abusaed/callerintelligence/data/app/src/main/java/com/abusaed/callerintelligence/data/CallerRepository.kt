package com.abusaed.callerintelligence.data

import android.content.Context
import com.abusaed.callerintelligence.PhoneNumberNormalizer
import com.abusaed.callerintelligence.model.CallerProfile

class CallerRepository(context: Context) {

    private val store = CallerProfileStore(context.applicationContext)

    fun findCaller(rawNumber: String?): CallerProfile? {
        val normalizedNumber = PhoneNumberNormalizer.normalize(rawNumber)
            ?: return null

        if (!PhoneNumberNormalizer.isUsableNumber(normalizedNumber)) {
            return null
        }

        return store.getProfile(normalizedNumber)
    }

    fun saveCaller(profile: CallerProfile) {
        val normalizedNumber = PhoneNumberNormalizer.normalize(
            profile.phoneNumber.value
        ) ?: return

        if (!PhoneNumberNormalizer.isUsableNumber(normalizedNumber)) {
            return
        }

        val normalizedProfile = profile.copy(
            phoneNumber = profile.phoneNumber.copy(
                value = normalizedNumber
            )
        )

        store.saveProfile(normalizedProfile)
    }

    fun deleteCaller(rawNumber: String?) {
        val normalizedNumber = PhoneNumberNormalizer.normalize(rawNumber)
            ?: return

        if (!PhoneNumberNormalizer.isUsableNumber(normalizedNumber)) {
            return
        }

        store.deleteProfile(normalizedNumber)
    }
}
