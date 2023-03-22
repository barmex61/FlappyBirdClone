package com.fatih.gamestate

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2

abstract class State (gsm:GameStateManager) {

    protected val mouse = Vector2()
    protected val cam = OrthographicCamera()

    protected abstract fun handleInput()
    abstract fun update(dt:Float)
    abstract fun render(sb:SpriteBatch)
    abstract fun dispose()
}
