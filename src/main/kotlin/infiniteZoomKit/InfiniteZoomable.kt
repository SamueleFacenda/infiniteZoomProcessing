package infiniteZoomKit

import processing.core.PApplet.log
import processing.core.PApplet.pow
import java.lang.Math.E

abstract class InfiniteZoomable(sketch: InfiniteZoomSketch, index: Int){

    private var scaleCoefficient = getJustCreatedScaleCoefficent()
    protected val sketch: InfiniteZoomSketch = sketch

    companion object Constants{
        const val NOT_VISIBLE_THRESHOLD = 1f
        const val TOO_LARGE_THRESHOLD = 2000f
        const val SPEED = 0.005f
    }

    init {
        scaleCoefficient = getJustCreatedScaleCoefficent() + index * getScaleCoefficentOffset()
    }

    private fun getFirstVisibleScaleCoefficent(): Float {
        return log(NOT_VISIBLE_THRESHOLD / getOuterWidth())
    }

    private fun getFirstVisibleInnerWidth(): Float {
        return pow(E.toFloat(), getFirstVisibleScaleCoefficent()) * getInnerWidth()
    }

    private fun getJustCreatedScaleCoefficent(): Float {
        return log(getFirstVisibleInnerWidth() / getOuterWidth())
    }

    private fun getScaleCoefficentOffset(): Float {
        return getFirstVisibleScaleCoefficent() - getJustCreatedScaleCoefficent()
    }

    protected abstract fun display()
    protected abstract fun getInnerWidth(): Float
    protected abstract fun getOuterWidth(): Float

    internal final fun isDeletable(): Boolean{
        // the element is too large to be visible
        return pow(E.toFloat(), scaleCoefficient) * getInnerWidth() > TOO_LARGE_THRESHOLD
    }

    internal final fun isVisibile(): Boolean{
        // the element is too small to be visible
        return pow(E.toFloat(), scaleCoefficient) * getOuterWidth() > NOT_VISIBLE_THRESHOLD
    }

    internal final fun displayWithScale(){
        sketch.pushMatrix()
        sketch.scale(pow(E.toFloat(), scaleCoefficient))
        scaleCoefficient += SPEED
        display()
        sketch.popMatrix()
    }
}