package com.fatih.sprites

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3

class Bird (posX : Float , posY : Float){

    companion object{
        private const val GRAVITY = -15f
        private const val MOVEMENT = 100f
    }
    private val texture = Texture("birdanimation.png")
    private var birdAnimation : Animation = Animation(TextureRegion(texture),3,0f)
    private val position = Vector3(posX,posY,0f)
    private val velocity = Vector3(0f,0f,0f)
    private val rectangle = Rectangle(posX,posY,texture.width.toFloat()/3f,texture.height.toFloat())
    private val sound = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"))

    fun update(dt : Float){
        birdAnimation.update(dt*50f)
        if (position.y > 0 )
            velocity.add(0f, GRAVITY,0f)
        velocity.scl(dt)
        position.add(MOVEMENT * dt ,velocity.y,0f)
        if (position.y < 0 ){
                position.y = 0f
        }
        velocity.scl(1/dt)
        rectangle.setPosition(position.x,position.y)
    }

    fun jump(){
        velocity.y = 250f
        sound.play(0.5f)
    }

    fun getBounds():Rectangle{
        return rectangle
    }

    fun getBird() = birdAnimation.getFrame()
    fun getPosition() = position

    fun dispose(){
        texture.dispose()
    }
}
