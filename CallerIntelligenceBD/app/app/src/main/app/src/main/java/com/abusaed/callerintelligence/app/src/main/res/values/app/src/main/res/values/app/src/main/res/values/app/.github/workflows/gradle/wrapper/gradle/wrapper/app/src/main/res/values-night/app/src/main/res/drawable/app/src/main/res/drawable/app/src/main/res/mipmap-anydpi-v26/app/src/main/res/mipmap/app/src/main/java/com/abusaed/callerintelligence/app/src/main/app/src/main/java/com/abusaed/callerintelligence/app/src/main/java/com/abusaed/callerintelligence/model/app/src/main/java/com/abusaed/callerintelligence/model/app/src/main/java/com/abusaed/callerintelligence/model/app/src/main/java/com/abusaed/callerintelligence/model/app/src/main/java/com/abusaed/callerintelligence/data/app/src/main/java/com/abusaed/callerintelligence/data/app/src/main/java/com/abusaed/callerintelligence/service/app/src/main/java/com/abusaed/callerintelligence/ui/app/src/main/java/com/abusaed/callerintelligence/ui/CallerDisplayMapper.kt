package com.abusaed.callerintelligence.ui

import com.abusaed.callerintelligence.model.CallerProfile
import com.abusaed.callerintelligence.model.FieldResult

object CallerDisplayMapper {

    fun map(profile: CallerProfile): List<CallerDisplayItem> {
        return listOf(
            createItem("Mobile Number", profile.phoneNumber),
            createItem("Name", profile.name),
            createItem("Father's Name", profile.fatherName),
            createItem("Mother's Name", profile.motherName),
            createItem("Spouse's Name", profile.spouseName),
            createItem("Operator", profile.operator),
            createItem("MCC/MNC", profile.mccMnc),
            createItem("Tower ID", profile.towerId),
            createItem("Tower Thana", profile.towerThana),
            createItem("Tower District", profile.towerDistrict),
            createItem("Tower Division", profile.towerDivision),
            createItem("Tower Village", profile.towerVillage),
            createItem("Tower Distance", profile.towerDistance)
        )
    }

    private fun createItem(
        title: String,
        field: FieldResult<String>
    ): CallerDisplayItem {
        return CallerDisplayItem(
            title = title,
            value = field.value ?: "Not available",
            status = field.status.name,
            source = field.source
        )
    }
}
