package com.fatih.gamestate

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.fatih.flappybird.FlappyBirdGame
import com.fatih.sprites.Bird
import com.fatih.sprites.Tube

class PlayState(private val gsm:GameStateManager) : State(gsm) {

    private val bird = Bird(50f,100f)
    private val bg = Texture("bg.png")
    private val tubes = com.badlogic.gdx.utils.Array<Tube>()
    private val ground = Texture("ground.png")
    private var groundPos1 = Vector2()
    private var groundPos2 = Vector2()

    companion object{
        private const val TUBE_SPACING = 125f
        private const val TUBE_COUNT = 4
        private const val GROUND_Y_OFFSET = -30f
    }

    init {
        for (index in 1..TUBE_COUNT){
            tubes.add(Tube(index * (TUBE_SPACING + Tube.TUBE_WIDTH)))
        }
        groundPos1 = Vector2(cam.position.x-cam.viewportWidth/2f, GROUND_Y_OFFSET)
        groundPos2 = Vector2((cam.position.x-cam.viewportWidth/2f) + ground.width, GROUND_Y_OFFSET)
        cam.setToOrtho(false,FlappyBirdGame.width/2f,FlappyBirdGame.height/2f)
    }

    override fun update(dt: Float) {
        handleInput()
        updateGround()
        bird.update(dt)
        cam.position.x = bird.getPosition().x + 30f
        for (i in 0 until tubes.size){
            if (cam.position.x - (cam.viewportWidth / 2f )> tubes[i].getTopTubePos().x + tubes[i].getTopTube().width){
                tubes[i].rePosition(tubes[i].getTopTubePos().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT))
            }
            if (tubes[i].collide(bird.getBounds())){
                gsm.set(PlayState(gsm))
            }
        }
        if (bird.getPosition().y <= ground.height + GROUND_Y_OFFSET){
            gsm.set(PlayState(gsm))
        }
        cam.update()
    }

    override fun handleInput() {
        if (Gdx.input.justTouched()){
            bird.jump()
        }
    }

    override fun render(sb: SpriteBatch) {
        sb.projectionMatrix = cam.combined
        sb.begin()
        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0f)
        sb.draw(bird.getBird(),bird.getPosition().x,bird.getPosition().y)
        for (tube in tubes){
            sb.draw(tube.getTopTube(),tube.getTopTubePos().x,tube.getTopTubePos().y)
            sb.draw(tube.getBottomTube(),tube.getBottomTubePos().x,tube.getBottomTubePos().y)
        }
        sb.draw(ground,groundPos1.x,groundPos1.y)
        sb.draw(ground,groundPos2.x,groundPos2.y)
        sb.end()
    }

    private fun updateGround(){
        if (cam.position.x - (cam.viewportWidth / 2f) > groundPos1.x + ground.width){
            groundPos1.add(ground.width * 2f , 0f)
        }
        if (cam.position.x - (cam.viewportWidth / 2f) > groundPos2.x + ground.width){
            groundPos2.add(ground.width * 2f , 0f)
        }
    }

    override fun dispose() {
        bg.dispose()
        bird.dispose()
        for (tube in tubes){
            tube.getBottomTube().dispose()
            tube.getTopTube().dispose()
        }
    }
}
