package infiniteZoomKit

abstract class FactoryCreator{
    abstract fun produce(sketch: InfiniteZoomSketch): InfiniteZoomable
    abstract fun produce(sketch: InfiniteZoomSketch, i: Int): InfiniteZoomable
}