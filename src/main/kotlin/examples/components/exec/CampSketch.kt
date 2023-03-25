package examples.components.exec

import examples.components.CampFire
import processing.core.PApplet

class CampSketch: PApplet() {

    private lateinit var camp: CampFire
    override fun settings() {
        size(800, 800, P3D)
    }

    override fun setup() {
        lights()
        camp = CampFire(this)
    }

    override fun draw() {
        background(255)
        camera(mouseX.toFloat(), -600f, 300f, 0f, 0f, 0f, 0f, 1f, 0f)
        scale(5f)
        camp.draw()
    }
}

fun main() {
    PApplet.runSketch(arrayOf("CampSketch"), CampSketch())
}