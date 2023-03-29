package examples.components

import processing.core.PApplet
import processing.core.PApplet.pow

class Splash(
    private val radius: Float,
    private val x: Float,
    private val z: Float,
    ) {
    private val functionVariable = radius/2f
    companion object{
        const val SPAN = 3
        const val N_STEPS = 10

        fun getNewSplashFromRainDrop(rainDrop: RainDrop, radius: Float): Splash{
            val (x, _, z) = rainDrop.getPosition()
            return Splash(radius, x, z)
        }
    }

    private var start = 0
    private var end = 0

    private fun parable(x: Float): Float{
        return pow(x - functionVariable, 2f) / functionVariable - functionVariable
    }

    fun draw(sketch: PApplet){
        sketch.stroke(0f, 50f, 200f)
        sketch.noFill()

        val step = radius / N_STEPS

        for (i in start..end) {
            val currentX = i * step
            val y = parable(currentX)

            sketch.pushMatrix()

            sketch.translate(x, y, z)
            sketch.rotateX(PApplet.PI/2f)
            sketch.circle(0f,0f,currentX)

            sketch.popMatrix()
        }

        if(end > SPAN)
            start++
        if(end < N_STEPS)
            end++
    }

    fun isFinished(): Boolean{
        return start >= N_STEPS
    }

}