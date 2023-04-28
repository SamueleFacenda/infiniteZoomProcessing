package infiniteZoomKit

abstract class FactoryCreator{
    abstract fun produceObjectOnSketch(sketch: InfiniteZoomSketch): InfiniteZoomable
    abstract fun produceObjectOnSketch(sketch: InfiniteZoomSketch, startSizeIndex: Int): InfiniteZoomable
}