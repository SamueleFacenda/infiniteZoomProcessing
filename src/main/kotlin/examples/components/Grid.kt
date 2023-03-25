package examples.components

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet
import processing.core.PApplet.exp

class Grid(sketch: InfiniteZoomSketch, index:Int, exponent: Int = 4): InfiniteZoomable(sketch, index) {

    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)
    // 2^exponent
    private val numberOfLines = 1 shl exponent
    companion object: FactoryCreator() {
        const val SIDE = 100
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return Grid(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return Grid(sketch, i)
        }
    }


    override fun display() {
        sketch.strokeWeight(1f/exp(getScaleCoefficent()))
        var i = -SIDE/2f
        val step = SIDE.toFloat() / numberOfLines
        while(i <= SIDE/2){
            sketch.line(-SIDE/2f, 0f, i.toFloat(), SIDE/2f, 0f, i.toFloat())
            sketch.line(i.toFloat(), 0f, -SIDE/2f, i.toFloat(), 0f, SIDE/2f)

            i += step
        }
    }

    override fun getInnerWidth(): Float {
        return SIDE/2f
    }

    override fun getOuterWidth(): Float {
        return SIDE.toFloat()
    }
}