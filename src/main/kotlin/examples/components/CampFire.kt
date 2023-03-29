package examples.components

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet.*
import kotlin.math.PI

class CampFire(sketch: InfiniteZoomSketch, index: Int): InfiniteZoomable(sketch, index) {

    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)

    companion object Factory: FactoryCreator(){
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return CampFire(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return CampFire(sketch, i)
        }
        const val N_TONGUE = 80
        const val N_ROCKS = 15
    }

    private val rain = Rain(
        sketch,
        20f,
        40f,
        60f,
        60f,
        Triple(
            sketch.random(-2f, 2f)/3,
            sketch.random(3f, 4f),
            sketch.random(-2f, 2f)/3,
        )
    )
    private var tongues = MutableList(N_TONGUE) { getNewTongue() }

    private fun getNewTongue(): FireTongue {
        return FireTongue(
            sketch,
            sketch.random(6f, 17f),
            (2f * PI / 1000f * sketch.random(1f, 1000f)).toFloat(),
            sketch.random(2f, 8f),
            60f,
            sketch.random(1f, 6f)/3,
            sketch.random(10f, 50f),
            -8f
        )
    }

    private val rocks = List(N_ROCKS) { Rock(sketch, 5) }

    override fun display() {
        sketch.strokeWeight(2/exp(getScaleCoefficent()))
        rain.draw()

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
    override fun getInnerWidth(): Float {
        return 10f
    }

    override fun getOuterWidth(): Float {
        return 50f
    }
}

