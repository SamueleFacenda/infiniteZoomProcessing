package examples.components

import processing.core.PApplet
import processing.core.PApplet.*
import processing.core.PConstants.QUAD_STRIP


class FireTongue(
    private val sketch: PApplet,
    private val radius: Float,
    private val angle: Float,
    private val width: Float,
    private val height: Float,
    private val speed: Float,
    private val span: Float,
    ) {

    // y=1/(1+e^-x)
    // 1+e^-x=i/y
    // e^-x=i/y-1
    // -x=ln(i/y-1)
    // x=-ln(i/y-1)
    // -4(x-1)=-ln(i/y-1)
    // x=ln(i/y-1)/4-1.5
    // Y=1/(1+e^(-4(x-1.5)))
    // con x da 0 a 3
    // y da 0 a 1
    private fun logistic(x: Float): Float {
        return 1f/(1f+exp(-4f*(1.5f-x)))
    }

    companion object Constants{
        const val PRECISION = 10
    }

    private var start = 0f
    private var end = 0f


    private val color = sketch.color(sketch.random(150f, 250f), sketch.random(0f, 100f), 0f)

    private fun arc() {
        sketch.beginShape(QUAD_STRIP)

        val step = (end-start) / PRECISION
        var y = start

        while(y < end){
            val i = (y/height)*3f
            sketch.vertex(
                cos(angle) * logistic(i) * radius,
                -y,
                sin(angle) * logistic(i) * radius
            )
            sketch.vertex(
                width + cos(angle) * logistic(i) * radius,
                -y,
                width + sin(angle) * logistic(i) * radius
            )
            y += step
        }

        sketch.endShape()
    }

    fun draw(){
        sketch.pushMatrix()
        sketch.fill(color)
        sketch.noStroke()
        arc()
        sketch.popMatrix()

        if(end + speed <= height) end += speed
        if((end - span > 0) and (start + speed <= height)) start += speed
    }

    fun isDead(): Boolean{
        return start + speed > height
    }
}



class Fire: PApplet() {
    override fun settings() {
        size(800, 800, P3D)
    }

    private val n = 200
    private val tongues = Array(n){
        FireTongue(
            this,
            100f,
            2 * PI / n * it,
            10f,
            400f,
            5f,
            100f
        )
    }

    override fun setup() {
        lights()
    }

    override fun draw() {
        background(220)
        translate(width / 2f, height / 2f)
        //rotateX(frameCount * 0.01f)
        //rotateY(frameCount * 0.01f)
        //rotateZ(frameCount * 0.01f)
        tongues.forEach { it.draw() }
        println(tongues.filter { !it.isDead() }.size)
    }
}

fun main() {
    runSketch(arrayOf("Fire"), Fire())
}