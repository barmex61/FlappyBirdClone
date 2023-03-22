@file:JvmName("DesktopLauncher")

package com.fatih.flappybird.desktop

import com.badlogic.gdx.Files
import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.fatih.flappybird.FlappyBirdGame

/** Launches the desktop (LWJGL) application. */
fun main() {
    LwjglApplication(FlappyBirdGame(), LwjglApplicationConfiguration().apply {
        title = "FlappyBird"
        width = 640
        height = 480
        intArrayOf(128, 64, 32, 16).forEach{
            addIcon("libgdx$it.png", Files.FileType.Internal)
        }
    })
}
