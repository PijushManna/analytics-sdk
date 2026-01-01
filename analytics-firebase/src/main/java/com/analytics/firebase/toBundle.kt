package com.analytics.firebase

import android.os.Bundle

fun Map<String, String>.toBundle(): Bundle {
    val bundle = Bundle()
    forEach { (key, value) ->
        bundle.putString(key, value)
    }
    return bundle
}
