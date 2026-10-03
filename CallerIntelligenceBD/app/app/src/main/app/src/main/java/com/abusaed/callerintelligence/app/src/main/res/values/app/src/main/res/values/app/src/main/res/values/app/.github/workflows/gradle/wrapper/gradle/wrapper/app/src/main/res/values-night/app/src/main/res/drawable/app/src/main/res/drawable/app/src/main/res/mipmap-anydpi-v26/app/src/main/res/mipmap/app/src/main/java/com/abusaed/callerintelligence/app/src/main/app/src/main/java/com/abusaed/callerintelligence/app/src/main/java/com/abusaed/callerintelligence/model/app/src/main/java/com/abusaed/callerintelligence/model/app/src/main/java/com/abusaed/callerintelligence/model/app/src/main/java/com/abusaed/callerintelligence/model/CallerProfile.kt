package com.abusaed.callerintelligence.model

data class CallerProfile(
    val phoneNumber: FieldResult<String> = FieldResult(),

    val name: FieldResult<String> = FieldResult(),
    val fatherName: FieldResult<String> = FieldResult(),
    val motherName: FieldResult<String> = FieldResult(),
    val spouseName: FieldResult<String> = FieldResult(),

    val operator: FieldResult<String> = FieldResult(),
    val mccMnc: FieldResult<String> = FieldResult(),

    val towerId: FieldResult<String> = FieldResult(),
    val towerThana: FieldResult<String> = FieldResult(),
    val towerDistrict: FieldResult<String> = FieldResult(),
    val towerDivision: FieldResult<String> = FieldResult(),
    val towerVillage: FieldResult<String> = FieldResult(),

    val towerDistance: FieldResult<String> = FieldResult(),

    val lastUpdated: Long = System.currentTimeMillis()
)
