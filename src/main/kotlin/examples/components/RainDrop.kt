package examples.components

import processing.core.PApplet

class RainDrop(
    private val lenghtRatio: Float,
    private val vector: Triple<Float, Float, Float>,
    private var position: Triple<Float, Float, Float>,
    ) {
    fun draw(sketch: PApplet){
        sketch.pushMatrix()
        sketch.stroke(0f, 50f, 200f)
        sketch.line(
            position.first,
            position.second,
            position.third,
            position.first + vector.first*lenghtRatio,
            position.second + vector.second*lenghtRatio,
            position.third + vector.third*lenghtRatio,
        )

        sketch.popMatrix()

        position = Triple(
            position.first + vector.first,
            position.second + vector.second,
            position.third + vector.third,
        )
    }

    fun getPosition(): Triple<Float, Float, Float>{
        return position
    }
}