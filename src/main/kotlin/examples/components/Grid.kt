package examples.components

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet.GROUP
import processing.core.PShape
import javax.accessibility.AccessibleExtendedText.LINE

class Grid(
    sketch: InfiniteZoomSketch,
    index:Int,
    private val exponent: Int = 3,
    ): InfiniteZoomable(sketch, index) {

    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)
    // 2^exponent
    private val numberOfLines = 1 shl exponent
    private val halfSide = 1 shl (exponent-1)

    init {
        initScaleCoefficient(index)
    }
    companion object: FactoryCreator() {
        private val instances: MutableMap<Int, PShape> = HashMap()
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return Grid(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return Grid(sketch, i)
        }
    }


    init {
        if (!instances.containsKey(exponent)) {
            val shape = sketch.createShape(GROUP)
            sketch.stroke(0)
            val toAdd = mutableListOf<PShape>()
            for (i in -halfSide..halfSide) {
                if (-halfSide / 2 <= i && i <= halfSide / 2) {
                    // line with a space in the center
                    toAdd.add(
                        sketch.createShape(
                            LINE,
                            -halfSide.toFloat(),
                            0f,
                            i.toFloat(),
                            -halfSide / 2f,
                            0f,
                            i.toFloat()
                        )
                    )
                    toAdd.add(
                        sketch.createShape(
                            LINE,
                            halfSide / 2f,
                            0f,
                            i.toFloat(),
                            halfSide.toFloat(),
                            0f,
                            i.toFloat()
                        )
                    )

                    toAdd.add(
                        sketch.createShape(
                            LINE,
                            i.toFloat(),
                            0f,
                            -halfSide.toFloat(),
                            i.toFloat(),
                            0f,
                            -halfSide / 2f
                        )
                    )
                    toAdd.add(
                        sketch.createShape(
                            LINE,
                            i.toFloat(),
                            0f,
                            halfSide / 2f,
                            i.toFloat(),
                            0f,
                            halfSide.toFloat()
                        )
                    )
                } else {
                    toAdd.add(
                        sketch.createShape(
                            LINE,
                            -halfSide.toFloat(),
                            0f,
                            i.toFloat(),
                            halfSide.toFloat(),
                            0f,
                            i.toFloat()
                        )
                    )
                    toAdd.add(
                        sketch.createShape(
                            LINE,
                            i.toFloat(),
                            0f,
                            -halfSide.toFloat(),
                            i.toFloat(),
                            0f,
                            halfSide.toFloat()
                        )
                    )
                }

                for (it in toAdd)
                    shape.addChild(it)
                toAdd.clear()
            }
            instances[exponent] = shape
        }
    }

    override fun display() {
        sketch.shape(instances[exponent]!!)
    }

    override fun getInnerWidth(): Float {
        return halfSide.toFloat()
    }

    override fun getOuterWidth(): Float {
        return numberOfLines.toFloat()
    }
}