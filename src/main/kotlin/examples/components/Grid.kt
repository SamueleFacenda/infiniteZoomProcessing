package examples.components

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet.GROUP
import javax.accessibility.AccessibleExtendedText.LINE

class Grid(sketch: InfiniteZoomSketch, index:Int, exponent: Int = 3): InfiniteZoomable(sketch, index) {

    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)
    // 2^exponent
    private val numberOfLines = 1 shl exponent
    private val halfSide = 1 shl (exponent-1)

    init {
        initScaleCoefficient(index)
    }
    companion object: FactoryCreator() {
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return Grid(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return Grid(sketch, i)
        }
    }

    private val shape = sketch.createShape(GROUP)

    init {
        sketch.stroke(0)
        for(i in -halfSide .. halfSide){
            shape.addChild(sketch.createShape(LINE, -halfSide.toFloat(), 0f, i.toFloat(), halfSide.toFloat(), 0f, i.toFloat()))
            shape.addChild(sketch.createShape(LINE,i .toFloat(), 0f, -halfSide.toFloat(), i.toFloat(), 0f, halfSide.toFloat()))
        }
    }


    override fun display() {
        sketch.shape(shape)
    }

    override fun getInnerWidth(): Float {
        return halfSide.toFloat()
    }

    override fun getOuterWidth(): Float {
        return numberOfLines.toFloat()
    }
}