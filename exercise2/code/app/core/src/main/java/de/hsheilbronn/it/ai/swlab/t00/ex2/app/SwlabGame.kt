package de.hsheilbronn.it.ai.swlab.t00.ex2.app

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class SwlabGame(communicationController: CommunicationController?) : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    var img: Texture? = null
    var demo: Texture? = null
    var one: Texture? = null
    var two: Texture? = null
    var isTwo = false
    override fun create() {
        batch = SpriteBatch()
        img = Texture("badlogic.jpg")
        one = Texture("button.png")
        two = Texture("button2.png")
        demo = one
        Gdx.input.inputProcessor = object : InputAdapter() {
            override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                //return super.touchDown(screenX, screenY, pointer, button);
                var screenY = screenY
                screenY = Gdx.graphics.height - screenY
                if (screenX >= 300 && screenX <= 600 && screenY >= 300 && screenY <= 400) {
                    if (!isTwo) {
                        demo = two
                        isTwo = true
                    } else {
                        demo = one
                        isTwo = false
                    }
                }
                return true
            }
        }
    }

    override fun render() {
        Gdx.gl.glClearColor(0f, 1f, 0f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        batch!!.begin()
        batch!!.draw(img, 0f, 0f)
        batch!!.draw(demo, 300f, 300f)
        batch!!.end()
    }

    override fun dispose() {
        batch!!.dispose()
        img!!.dispose()
    }
}