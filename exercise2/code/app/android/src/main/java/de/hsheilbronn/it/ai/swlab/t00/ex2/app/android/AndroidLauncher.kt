package de.hsheilbronn.it.ai.swlab.t00.ex2.app.android

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import de.hsheilbronn.it.ai.swlab.t00.ex2.app.CommunicationController
import de.hsheilbronn.it.ai.swlab.t00.ex2.app.SwlabGame

/** Launches the Android application.  */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration = AndroidApplicationConfiguration()
        initialize(SwlabGame(CommunicationControllerImpl()), configuration)
    }
}