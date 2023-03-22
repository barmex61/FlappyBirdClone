package com.fatih.flappybird

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Music
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.fatih.gamestate.GameStateManager
import com.fatih.gamestate.MenuState

class FlappyBirdGame : ApplicationAdapter(){

    companion object{
        const val width = 360
        const val height = 640
        const val title = "Flappy Bird"
    }

    private lateinit var music : Music
    private val gsm = GameStateManager()
    private lateinit var spriteBatch: SpriteBatch

    override fun create() {
        spriteBatch = SpriteBatch()
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3")).apply {
            isLooping = true
            volume = 0.1f
            play()
        }
        Gdx.gl.glClearColor(1f,0f,0f,1f)
        gsm.pushState(MenuState(gsm))
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gsm.update(Gdx.graphics.deltaTime)
        gsm.render(spriteBatch)
    }

    override fun dispose() {
        super.dispose()
        music.dispose()
    }

}
