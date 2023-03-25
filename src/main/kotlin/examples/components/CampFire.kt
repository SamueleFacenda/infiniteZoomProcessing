package examples.components

import processing.core.PApplet
import processing.core.PApplet.*
import kotlin.math.PI

class CampFire(private val sketch: PApplet) {

    companion object Constants {
        const val N_TONGUE = 80
        const val N_ROCKS = 15
    }

    private var tongues = MutableList(N_TONGUE) { getNewTongue() }

    private fun getNewTongue(): FireTongue {
        return FireTongue(
            sketch,
            sketch.random(8f, 17f),
            (2f * PI / 1000f * sketch.random(1f, 1000f)).toFloat(),
            sketch.random(2f, 8f),
            60f,
            sketch.random(1f, 6f)/10,
            sketch.random(10f, 50f),
            -8f
        )
    }

    private val rocks = List(N_ROCKS) { Rock(sketch, 5) }

    fun draw() {
        tongues.forEach { it.draw() }
        tongues.replaceAll { if (it.isDead()) getNewTongue() else it }

        rocks.forEachIndexed { i, r ->
            sketch.pushMatrix()
            sketch.translate(
                sin(i.toFloat() / N_ROCKS * 2 * PI.toFloat()) * 20,
                -2.5f,
                cos(i.toFloat() / N_ROCKS * 2 * PI.toFloat()) * 20,
            )
            sketch.sphere(1f)
            r.draw()
            sketch.popMatrix()
        }
    }
}

