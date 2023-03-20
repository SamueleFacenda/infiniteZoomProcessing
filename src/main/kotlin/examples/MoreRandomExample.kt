package examples

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet

class MoreRandomExample(sketch: InfiniteZoomSketch, index: Int): InfiniteZoomable(sketch, index) {

    private val colors: Array<Int>
    private val heigths: Array<Float>
    private val positions: Array<FloatArray> = arrayOf(
        floatArrayOf(0f, 2f),
        floatArrayOf(1f, 2f),
        floatArrayOf(2f, 2f),
        floatArrayOf(2f, 1f),
        floatArrayOf(2f, 0f),
        floatArrayOf(2f, -1f),
        floatArrayOf(2f, -2f),
        floatArrayOf(1f, -2f),
        floatArrayOf(0f, -2f),
        floatArrayOf(-1f, -2f),
        floatArrayOf(-2f, -2f),
        floatArrayOf(-2f, -1f),
        floatArrayOf(-2f, 0f),
        floatArrayOf(-2f, 1f),
        floatArrayOf(-2f, 2f),
        floatArrayOf(-1f, 2f)
    )

    private var SIZE = 20f

    init {
        initScaleCoefficient(index)

        colors = Array(16) {
            sketch.color(
                sketch.random(0f, 255f),
                sketch.random(0f, 255f),
                sketch.random(0f, 255f)
            )
        }

        heigths = Array(16) { sketch.random(0f, 100f) }
    }

    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)

    companion object Factory: FactoryCreator(){
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return MoreRandomExample(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return MoreRandomExample(sketch, i)
        }
    }

    override fun display() {
        for (i in 0 until 16) {
            sketch.pushMatrix()
            sketch.fill(colors[i])
            sketch.translate(positions[i][0] * SIZE, -heigths[i]/2f, positions[i][1] * SIZE)
            sketch.box(SIZE, heigths[i], SIZE)
            sketch.popMatrix()
        }
    }

    override fun getInnerWidth(): Float {
        return SIZE * 3f
    }

    override fun getOuterWidth(): Float {
        return SIZE * 5f
    }
}

fun main() {
    val sketch = InfiniteZoomSketch(MoreRandomExample, true)
    PApplet.runSketch(arrayOf("InfiniteZoomSketch"), sketch)
}