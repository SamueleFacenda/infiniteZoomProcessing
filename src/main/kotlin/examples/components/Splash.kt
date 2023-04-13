package examples.components

import processing.core.PApplet
import processing.core.PApplet.*
import processing.core.PShape

class Splash(
    radius: Float,
    private val x: Float,
    private val z: Float,
    private val sketch: PApplet,
    ) {
    private val functionVariable = radius/2f

    private val key = (functionVariable * 10000).toInt()
    companion object{
        const val SPAN = 3
        const val N_STEPS = 10
        const val RESOLUTION = 2

        private val instances: MutableMap<Int, List<PShape>> = HashMap()

        fun getNewSplashFromRainDrop(rainDrop: RainDrop, radius: Float, sketch: PApplet): Splash{
            val (x, _, z) = rainDrop.getPosition()
            return Splash(radius, x, z, sketch)
        }
    }

    init {
        if(!instances.containsKey(key)){
            val shapes = mutableListOf<PShape>()
            val step = radius / N_STEPS
            var start = 0
            var end = 0

            sketch.stroke(20f, 80f, 220f)
            sketch.noFill()
            sketch.strokeWeight(1f)

            while(start < N_STEPS){
                val shape = sketch.createShape(GROUP)
                for (i in (start * RESOLUTION)..(end * RESOLUTION)) {
                    val currentRadius = i * step / RESOLUTION
                    val y = parable(currentRadius)
                    val ellipse = sketch.createShape(ELLIPSE, 0f, 0f, currentRadius, currentRadius)
                    ellipse.rotateX(PI/2f)
                    ellipse.translate(0f, y, 0f)

                    shape.addChild(ellipse)
                }
                shapes.add(shape)
                if(end > SPAN)
                    start++
                if(end < N_STEPS)
                    end++
            }
            instances[key] = shapes
        }
    }

    private fun parable(x: Float): Float{
        return pow(x - functionVariable, 2f) / functionVariable - functionVariable
    }

    private val iterator = instances[key]!!.iterator()

    fun draw(){
        val shape = iterator.next()
        sketch.pushMatrix()
        sketch.translate(x, 0f, z)
        sketch.shape(shape)
        sketch.popMatrix()
    }

    fun isFinished(): Boolean{
        return !iterator.hasNext()
    }

}