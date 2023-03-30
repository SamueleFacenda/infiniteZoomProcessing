package examples.components.exec

import examples.components.CampFire
import infiniteZoomKit.InfiniteZoomSketch
import processing.core.PApplet


fun main() {
    val sketch = InfiniteZoomSketch(CampFire, false, 4, true, 60)
    PApplet.runSketch(arrayOf("InfiniteZoomSketch"), sketch)
}