package com.fatih.sprites

import com.badlogic.gdx.graphics.g2d.TextureRegion

class Animation(private val region : TextureRegion , private val frameCount :Int , private val cycleTime : Float) {

    private var maxFrameTime :Float
    private var frames : ArrayList<TextureRegion> = ArrayList()
    private var currentFrameTime : Float = 0f
    private var frame : Int

    init {
        val frameWidth = region.regionWidth / frameCount
        for (i in 0..frameCount){
            frames.add(TextureRegion(region,i *frameWidth,0,frameWidth,region.regionHeight))
        }
        maxFrameTime = cycleTime / frameCount
        frame = 0
    }

    fun update(dt:Float){
        currentFrameTime +=dt
        if (currentFrameTime>maxFrameTime){
            frame++
            currentFrameTime = 0f
        }
        if (frame >= frameCount){
            frame = 0
        }
    }

    fun getFrame():TextureRegion{
        return frames[frame]
    }

}
