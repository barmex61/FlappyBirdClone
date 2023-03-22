package com.fatih.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector2
import java.util.Random

class Tube (private val x :Float){
    private val topTube = Texture("bottomtube.png")
    private val bottomTube = Texture("toptube.png")
    private var posBottomTube = Vector2()
    private var posTopTube = Vector2()
    private var random = Random()
    private lateinit var boundsTop : Rectangle
    private lateinit var boundsBottom :Rectangle

    companion object{
        private const val FLUCTUATION = 130f
        private const val TUBE_GAP = 100f
        private const val LOWEST_OPENING = 120f
        const val TUBE_WIDTH = 52f
    }

    init {
        posTopTube = Vector2(x,random.nextFloat(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING)
        posBottomTube = Vector2(x,posTopTube.y- TUBE_GAP - bottomTube.height)
        boundsTop = Rectangle(posTopTube.x,posTopTube.y,topTube.width.toFloat(),topTube.height.toFloat())
        boundsBottom = Rectangle(posBottomTube.x,posBottomTube.y,bottomTube.width.toFloat(),bottomTube.height.toFloat())
    }

    fun rePosition(x : Float){
        posTopTube.set(x,random.nextFloat(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING)
        posBottomTube.set(x,posTopTube.y- TUBE_GAP - bottomTube.height)
        boundsTop.setPosition(posTopTube)
        boundsBottom.setPosition(posBottomTube)
    }

    fun collide(rectangle: Rectangle): Boolean {
        return (rectangle.overlaps(boundsTop) || rectangle.overlaps(boundsBottom))
    }

    fun getBottomTube() = bottomTube
    fun getTopTube() = topTube
    fun getBottomTubePos() = posBottomTube
    fun getTopTubePos() = posTopTube
}
