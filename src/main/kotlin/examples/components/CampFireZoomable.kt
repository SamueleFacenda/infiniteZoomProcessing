package examples.components

import infiniteZoomKit.FactoryCreator
import infiniteZoomKit.InfiniteZoomSketch
import infiniteZoomKit.InfiniteZoomable
import processing.core.PApplet

class CampFireZoomable(sketch: InfiniteZoomSketch, index: Int): InfiniteZoomable(sketch, index) {
    constructor(sketch: InfiniteZoomSketch): this(sketch, 0)

    companion object Factory: FactoryCreator(){
        override fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable {
            return CampFireZoomable(sketch)
        }

        override fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable {
            return CampFireZoomable(sketch, i)
        }
    }

    private val campFire = CampFire(sketch)
    override fun display() {
        campFire.draw()
    }

    override fun getInnerWidth(): Float {
        return 15f
    }

    override fun getOuterWidth(): Float {
        return 50f
    }
}

fun main() {
    val sketch = InfiniteZoomSketch(CampFireZoomable, true, 2)
    PApplet.runSketch(arrayOf("InfiniteZoomSketch"), sketch)
}