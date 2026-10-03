package com.abusaed.callerintelligence.data

import android.content.Context
import com.abusaed.callerintelligence.model.CallerProfile
import com.abusaed.callerintelligence.model.FieldResult
import com.abusaed.callerintelligence.model.VerificationStatus
import org.json.JSONObject

class CallerProfileStore(context: Context) {

    private val preferences = context.getSharedPreferences(
        "caller_profiles",
        Context.MODE_PRIVATE
    )

    fun saveProfile(profile: CallerProfile) {
        val phoneNumber = profile.phoneNumber.value ?: return

        val json = JSONObject().apply {
            putField("phoneNumber", profile.phoneNumber)
            putField("name", profile.name)
            putField("fatherName", profile.fatherName)
            putField("motherName", profile.motherName)
            putField("spouseName", profile.spouseName)
            putField("operator", profile.operator)
            putField("mccMnc", profile.mccMnc)
            putField("towerId", profile.towerId)
            putField("towerThana", profile.towerThana)
            putField("towerDistrict", profile.towerDistrict)
            putField("towerDivision", profile.towerDivision)
            putField("towerVillage", profile.towerVillage)
            putField("towerDistance", profile.towerDistance)
            put("lastUpdated", profile.lastUpdated)
        }

        preferences.edit()
            .putString(phoneNumber, json.toString())
            .apply()
    }

    fun getProfile(phoneNumber: String): CallerProfile? {
        val jsonText = preferences.getString(phoneNumber, null)
            ?: return null

        return try {
            val json = JSONObject(jsonText)

            CallerProfile(
                phoneNumber = readField(json, "phoneNumber"),
                name = readField(json, "name"),
                fatherName = readField(json, "fatherName"),
                motherName = readField(json, "motherName"),
                spouseName = readField(json, "spouseName"),
                operator = readField(json, "operator"),
                mccMnc = readField(json, "mccMnc"),
                towerId = readField(json, "towerId"),
                towerThana = readField(json, "towerThana"),
                towerDistrict = readField(json, "towerDistrict"),
                towerDivision = readField(json, "towerDivision"),
                towerVillage = readField(json, "towerVillage"),
                towerDistance = readField(json, "towerDistance"),
                lastUpdated = json.optLong(
                    "lastUpdated",
                    System.currentTimeMillis()
                )
            )
        } catch (_: Exception) {
            null
        }
    }

    fun deleteProfile(phoneNumber: String) {
        preferences.edit()
            .remove(phoneNumber)
            .apply()
    }

    private fun JSONObject.putField(
        key: String,
        field: FieldResult<String>
    ) {
        put(
            key,
            JSONObject().apply {
                put("value", field.value)
                put("status", field.status.name)
                put("source", field.source)
                put("note", field.note)
            }
        )
    }

    private fun readField(
        json: JSONObject,
        key: String
    ): FieldResult<String> {
        val fieldJson = json.optJSONObject(key)
            ?: return FieldResult()

        val status = try {
            VerificationStatus.valueOf(
                fieldJson.optString(
                    "status",
                    VerificationStatus.NOT_AVAILABLE.name
                )
            )
        } catch (_: Exception) {
            VerificationStatus.NOT_AVAILABLE
        }

        return FieldResult(
            value = fieldJson.optString("value", null),
            status = status,
            source = fieldJson.optString("source", null),
            note = fieldJson.optString("note", null)
        )
    }
}
