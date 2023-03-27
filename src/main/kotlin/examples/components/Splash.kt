package examples.components

import processing.core.PApplet
import processing.core.PApplet.abs
import processing.core.PApplet.pow

class Splash(
    radius: Float,
    private val x: Float,
    private val z: Float,
    ) {
    private val functionVariable = radius/2f
    companion object{
        const val SPAN = 10
    }

    private var start = 0
    private var end = 0

    private fun parabole(x: Float): Float{
        return pow(x - functionVariable, 2f) / functionVariable - functionVariable
    }

    fun draw(sketch: PApplet){
        sketch.stroke(0f, 50f, 200f)
        sketch.noFill()

        for (i in start..end) {
            val y = parabole(i.toFloat())

            sketch.pushMatrix()

            sketch.translate(x, y, z)
            sketch.rotateX(PApplet.PI/4f)
            sketch.circle(0f,0f,i.toFloat())

            sketch.popMatrix()
        }

        if(end > SPAN)
            start++
        if(parabole(end.toFloat()) <= 0)
            end++
    }

    fun isFinished(): Boolean{
        return parabole(end.toFloat()) > 0
    }
}