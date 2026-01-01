package com.analytics.core

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimestampFormatter {

    private val formatter = SimpleDateFormat(
        "dd, MMM, yyyy h:mm a",
        Locale.ENGLISH
    )

    fun format(timestampMillis: Long): String {
        return formatter.format(Date(timestampMillis))
    }
}