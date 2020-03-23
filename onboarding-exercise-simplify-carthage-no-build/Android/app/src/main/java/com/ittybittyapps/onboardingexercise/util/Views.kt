package com.ittybittyapps.onboardingexercise.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

@Suppress("UNCHECKED_CAST") // This is how we roll until type safe ViewBinding is out.
fun <T : View> ViewGroup.inflate(@LayoutRes layout: Int, attach: Boolean = false): T =
    LayoutInflater.from(context).inflate(layout, this, attach) as T
