package examples.components.exec

import examples.components.CampFire
import infiniteZoomKit.InfiniteZoomSketch
import processing.core.PApplet


fun main() {
    val sketch = InfiniteZoomSketch(CampFire, true, 4, true)
    PApplet.runSketch(arrayOf("InfiniteZoomSketch"), sketch)
}