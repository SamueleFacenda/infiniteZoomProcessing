package examples.components

import processing.core.PApplet
import kotlin.math.sqrt

class Rain(
    private val sketch: PApplet,
    private val innerRadius: Float,
    private val outerRadius: Float,
    private val coneHeight: Float,
    private val cilinderHeight: Float,
    private val rainDropVector: Triple<Float, Float, Float>
    ) {

    companion object{
        const val NUMBER_OF_RAINDROPS = 200
    }

    private val rainDrops = MutableList(NUMBER_OF_RAINDROPS) { getNewRainDrop() }

    private fun getNewRainDrop(): RainDrop {
        return RainDrop(
            sketch.random(1f, 3f),
            Triple(
                rainDropVector.first + sketch.random(-1f, 1f)/10,
                rainDropVector.second + sketch.random(-1f, 1f)/10,
                rainDropVector.third + sketch.random(-1f, 1f)/10,
            ),
            getNewPosition()
        )
    }

    private fun getNewPosition(): Triple<Float, Float, Float>{
        val x = sketch.random(-outerRadius, outerRadius)
        val z = sketch.random(-outerRadius, outerRadius)
        var y = cilinderHeight + coneHeight * sqrt(x*x + z*z)/outerRadius
        y = -y
        return Triple(x, y, z)
    }

    private fun getInnerCilinderHeight(): Float{
        return outerRadius/coneHeight*innerRadius
    }

    private fun getInnerConeHeight(): Float{
        return outerRadius/cilinderHeight*innerRadius
    }

    private fun isValid(drop: RainDrop): Boolean{
        val pos = drop.getPosition()
        val x = pos.first
        val y = pos.second
        val z = pos.third
        val posRadio = sqrt(x*x + z*z)

        if(y > 0)
            return false

        if(posRadio > outerRadius)
            return false

        if(y<getInnerCilinderHeight() && posRadio < innerRadius)
            return false

        if(posRadio < innerRadius &&
            getInnerCilinderHeight() > y &&
            y < getInnerCilinderHeight() + getInnerConeHeight() * posRadio/innerRadius)
            return false

        return true
    }

    fun draw() {
        rainDrops.forEach { it.draw(sketch) }
        rainDrops.replaceAll { if(isValid(it)) it else getNewRainDrop() }
    }
}