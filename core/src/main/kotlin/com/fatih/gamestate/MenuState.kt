package com.fatih.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.fatih.flappybird.FlappyBirdGame

class MenuState(private val gsm:GameStateManager) : State(gsm) {

    private val background : Texture = Texture("bg.png")
    private val playButton : Texture = Texture("playbtn.png")

    init {
        cam.setToOrtho(false,FlappyBirdGame.width/2f,FlappyBirdGame.height/2f)
    }

    override fun update(dt: Float) {
        handleInput()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            gsm.set(PlayState(gsm))
        }
    }

    override fun dispose() {
        background.dispose()
        playButton.dispose()
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(background,0f,0f)
        sb.draw(playButton,cam.position.x-playButton.width/2f,cam.position.y)
        sb.end()
    }
}
