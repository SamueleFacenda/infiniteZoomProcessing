package examples.components.exec

import examples.components.Splash
import processing.core.PApplet

class Splasher: PApplet() {
    override fun settings() {
        size(800, 800, P3D)
    }

    override fun setup() {
        frameRate(60f)
    }

    private var splash = Splash(100f, 0f, 0f, this)

    override fun draw() {
        background(255)
        lights()
        camera(mouseX.toFloat(), mouseY.toFloat(), 200f, 0f, 0f, 0f, 0f, 1f, 0f)

        splash.draw()

        if(splash.isFinished())
            splash = Splash(100f, 0f, 0f, this)
    }
}

fun main() {
    PApplet.main("examples.components.exec.Splasher")
}