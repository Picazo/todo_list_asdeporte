package com.juliopicazo.todolist.utils

import android.text.Editable
import android.text.SpannableStringBuilder

fun String.toEditable(): Editable {
    return SpannableStringBuilder(this)
}