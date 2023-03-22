package com.fatih.gamestate

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.Stack

class GameStateManager {

    private val states = Stack<State>()

    fun pushState(state:State){
        states.push(state)
    }

    fun popState(){
        states.pop().dispose()
    }

    fun set(state: State){
        popState()
        pushState(state)
    }

    fun update(dt:Float){
        states.peek().update(dt)
    }

    fun render(sb:SpriteBatch){
        states.peek().render(sb)
    }

}
