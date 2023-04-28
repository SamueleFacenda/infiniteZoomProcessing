package infiniteZoomKit

import processing.core.PApplet.*

/**
 * @author: Samuele Facenda
 * @since: 1.0
 *
 * An abstract class that represents an object that can be zoomed infinitely.
 * @param sketch the sketch that contains the object
 * @param index used for the initial scale coefficient, 0 by default, the smaller
 */
abstract class InfiniteZoomable(protected val sketch: InfiniteZoomSketch, index: Int){

    private var scaleCoefficient = initScaleCoefficient(index)

    companion object Constants{
        const val NOT_VISIBLE_THRESHOLD = 2f
        const val TOO_LARGE_THRESHOLD = 3000f
        const val SPEED = 0.01f
    }

    protected fun initScaleCoefficient(index: Int): Float{
        scaleCoefficient = getJustCreatedScaleCoefficent() + index * getScaleCoefficentOffset()
        return scaleCoefficient
    }

    private fun getFirstVisibleScaleCoefficent(): Float {
        return log(NOT_VISIBLE_THRESHOLD / getOuterWidth())
    }

    private fun getFirstVisibleInnerWidth(): Float {
        return exp(getFirstVisibleScaleCoefficent()) * getInnerWidth()
    }

    private fun getJustCreatedScaleCoefficent(): Float {
        return log(getFirstVisibleInnerWidth() / getOuterWidth())
    }

    private fun getScaleCoefficentOffset(): Float {
        return getFirstVisibleScaleCoefficent() - getJustCreatedScaleCoefficent()
    }

    protected abstract fun draw()

    /**
     * @return the size of the "hole" inside the object
     */
    protected abstract fun getInnerWidth(): Float

    protected abstract fun getOuterWidth(): Float

    internal fun isDeletable(): Boolean{
        return exp(scaleCoefficient) * getInnerWidth() > TOO_LARGE_THRESHOLD
    }

    internal fun isVisibile(): Boolean{
        return exp(scaleCoefficient) * getOuterWidth() > NOT_VISIBLE_THRESHOLD
    }

    internal fun drawWithScale(){
        sketch.pushMatrix()
        sketch.pushStyle()
        sketch.scale(exp(scaleCoefficient))
        scaleCoefficient += SPEED
        draw()
        sketch.popStyle()
        sketch.popMatrix()
    }

    protected fun getScaleCoefficent(): Float {
        return scaleCoefficient
    }
}