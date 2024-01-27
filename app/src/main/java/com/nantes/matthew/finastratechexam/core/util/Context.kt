package com.nantes.matthew.finastratechexam.core.util

import android.content.Context
import android.content.res.Configuration

fun Context.isPortrait(): Boolean =
    this.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT