package com.abusaed.callerintelligence

object PhoneNumberNormalizer {

    fun normalize(rawNumber: String?): String? {
        if (rawNumber.isNullOrBlank()) {
            return null
        }

        val cleaned = rawNumber
            .trim()
            .replace(" ", "")
            .replace("-", "")
            .replace("(", "")
            .replace(")", "")

        if (cleaned.isBlank()) {
            return null
        }

        return when {
            cleaned.startsWith("+") -> {
                "+" + cleaned.substring(1).filter { it.isDigit() }
            }

            cleaned.startsWith("00") -> {
                "+" + cleaned.substring(2).filter { it.isDigit() }
            }

            else -> {
                cleaned.filter { it.isDigit() }
            }
        }
    }

    fun isUsableNumber(number: String?): Boolean {
        if (number.isNullOrBlank()) {
            return false
        }

        val digits = number.filter { it.isDigit() }

        return digits.length >= 7
    }
}
