package com.development.cursoandroid.ui.tools

import com.development.cursoandroid.application.ApiConstants

fun String.getImageUrl(): String {
    return ApiConstants.IMAGE_URL + this
}