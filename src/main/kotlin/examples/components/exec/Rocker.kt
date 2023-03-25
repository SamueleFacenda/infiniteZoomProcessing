package examples.components.exec

import examples.components.Rock
import processing.core.PApplet

class Rocker: PApplet() {
    private lateinit var rock: Rock

    override fun settings() {
        size(800, 800, P3D)
    }

    override fun setup() {
        rock = Rock(this, 100)
        lights()
    }

    override fun draw() {
        background(220)
        translate(width / 2f, height / 2f)
        rotateX(frameCount * 0.01f)
        rotateY(frameCount * 0.01f)
        rotateZ(frameCount * 0.01f)
        rock.draw()
    }
}

fun main() {
    PApplet.runSketch(arrayOf("Rocker"), Rocker())
}