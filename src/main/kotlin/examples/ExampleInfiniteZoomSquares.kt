package examples

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet

class ExampleInfiniteZoom(sketch: InfiniteZoomSketch, index: Int): InfiniteZoomable(sketch, index) {

    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)

    companion object Factory: FactoryCreator(){
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return ExampleInfiniteZoom(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return ExampleInfiniteZoom(sketch, i)
        }
    }

    override fun draw() {
        sketch.fill(150f)

        sketch.pushMatrix()
        sketch.translate(30f, -20f, 30f)
        sketch.box(20f, 40f, 20f)
        sketch.popMatrix()

        sketch.pushMatrix()
        sketch.translate(30f, -15f, -30f)
        sketch.box(20f, 30f, 20f)
        sketch.popMatrix()

        sketch.pushMatrix()
        sketch.translate(-30f, -10f, 30f)
        sketch.box(20f, 20f, 20f)
        sketch.popMatrix()

        sketch.pushMatrix()
        sketch.translate(-30f, -5f, -30f)
        sketch.box(20f, 10f, 20f)
        sketch.popMatrix()
    }

    override fun getInnerWidth(): Float {
        return 40f
    }

    override fun getOuterWidth(): Float {
        return 80f
    }
}

fun main() {
    val sketch = InfiniteZoomSketch(ExampleInfiniteZoom, true)
    PApplet.runSketch(arrayOf("InfiniteZoomSketch"), sketch)
}