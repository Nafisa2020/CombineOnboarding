package com.ittybittyapps.onboardingexercise

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.bluelinelabs.conductor.ChangeHandlerFrameLayout
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.ittybittyapps.onboardingexercise.home.HomeController
import com.ittybittyapps.onboardingexercise.util.asTransaction

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootContainer: ViewGroup = ChangeHandlerFrameLayout(this)
        setContentView(rootContainer)
        router = Conductor.attachRouter(this, rootContainer, savedInstanceState)

        if (!router.hasRootController()) {
            router.setRoot(HomeController().asTransaction())
        }
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            // Guard against a leak introduced in Android 10.
            // https://twitter.com/Piwai/status/1169274622614704129
            finishAfterTransition()
        }
    }
}
