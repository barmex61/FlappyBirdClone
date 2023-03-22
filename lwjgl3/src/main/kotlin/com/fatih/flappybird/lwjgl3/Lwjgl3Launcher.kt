@file:JvmName("Lwjgl3Launcher")

package com.fatih.flappybird.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.fatih.flappybird.FlappyBirdGame

/** Launches the desktop (LWJGL3) application. */
fun main() {
    Lwjgl3Application(FlappyBirdGame(), Lwjgl3ApplicationConfiguration().apply {
        setTitle(FlappyBirdGame.title)
        setWindowedMode(FlappyBirdGame.width, FlappyBirdGame.height)
        setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
    })
}
