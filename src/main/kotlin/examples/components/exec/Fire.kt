package examples.components.exec

import examples.components.FireTongue
import processing.core.PApplet

class Fire: PApplet() {
    override fun settings() {
        size(800, 800, P3D)
    }

    private val n = 200
    private val tongues = Array(n){
        FireTongue(
            this,
            100f,
            2 * PI / n * it,
            10f,
            400f,
            1f,
            100f,
            0f
        )
    }

    override fun setup() {
        lights()
    }

    override fun draw() {
        background(220)
        translate(width / 2f, height / 2f)
        //rotateX(frameCount * 0.01f)
        //rotateY(frameCount * 0.01f)
        //rotateZ(frameCount * 0.01f)
        tongues.forEach { it.draw() }
        println(tongues.filter { !it.isDead() }.size)
    }
}

fun main() {
    PApplet.runSketch(arrayOf("Fire"), Fire())
}