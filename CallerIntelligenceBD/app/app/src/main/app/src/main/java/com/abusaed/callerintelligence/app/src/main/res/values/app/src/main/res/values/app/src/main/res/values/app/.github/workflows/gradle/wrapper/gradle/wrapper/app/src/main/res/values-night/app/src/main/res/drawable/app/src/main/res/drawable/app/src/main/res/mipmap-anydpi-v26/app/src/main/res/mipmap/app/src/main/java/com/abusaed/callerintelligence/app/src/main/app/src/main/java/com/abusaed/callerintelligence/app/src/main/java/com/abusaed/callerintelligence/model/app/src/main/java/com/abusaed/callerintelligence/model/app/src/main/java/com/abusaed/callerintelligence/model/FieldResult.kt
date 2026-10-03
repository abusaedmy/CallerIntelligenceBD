package com.abusaed.callerintelligence.model

data class FieldResult<T>(
    val value: T? = null,
    val status: VerificationStatus = VerificationStatus.NOT_AVAILABLE,
    val source: String? = null,
    val note: String? = null
)
