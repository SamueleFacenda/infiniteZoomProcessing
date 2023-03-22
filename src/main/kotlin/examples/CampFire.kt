package examples

import examples.components.FireTongue
import examples.components.Rock
import processing.core.PApplet
import processing.core.PApplet.*
import kotlin.math.PI

class CampFire(private val sketch: PApplet) {

    companion object Constants {
        const val N_TONGUE = 100
        const val N_ROCKS = 20
    }

    private var tongues = MutableList(N_TONGUE) { getNewTongue() }

    private fun getNewTongue(): FireTongue {
        return FireTongue(
            sketch,
            20f,
            (2f * PI / sketch.random(1000f)).toFloat(),
            sketch.random(1f, 5f),
            60f,
            sketch.random(10f, 60f),
            sketch.random(1f, 5f) / 10
        )
    }

    private val rocks = List(N_ROCKS) { Rock(sketch, 5) }

    fun draw() {
        tongues.forEach { it.draw() }
        tongues.replaceAll { if (it.isDead()) getNewTongue() else it }

        rocks.forEachIndexed { i, r ->
            sketch.pushMatrix()
            sketch.translate(
                sin(i / N_ROCKS * 2 * PI.toFloat()) * 20,
                2.5f,
                cos(i / N_ROCKS * 2 * PI.toFloat()) * 20,
            )
            r.draw()
            sketch.popMatrix()
        }
    }
}
class CampSketch: PApplet() {

    private val camp = CampFire(this)
    override fun settings() {
        size(800, 800, P3D)
    }

    override fun setup() {
        lights()
    }

    override fun draw() {
        scale(5f)
        camp.draw()
    }
}

fun main() {
    runSketch(arrayOf("CampSketch"), CampSketch())
}