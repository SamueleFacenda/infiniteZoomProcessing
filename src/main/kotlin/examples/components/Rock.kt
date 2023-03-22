package examples.components

import processing.core.PApplet
import processing.core.PConstants.CLOSE
import processing.core.PConstants.GROUP
import processing.core.PShape
import kotlin.math.sqrt

class Rock(private val sketch: PApplet,private val circumrathio: Int) {
    companion object Static{
        private val PHI = (1f + sqrt(5.0).toFloat()) / 2f
        val VERTEX_COORS= arrayOf(
            Triple(1f,1f,1f),
            Triple(1f,1f,-1f),
            Triple(1f,-1f,1f),
            Triple(1f,-1f,-1f),
            Triple(-1f,1f,1f),
            Triple(-1f,1f,-1f),
            Triple(-1f,-1f,1f),
            Triple(-1f,-1f,-1f),
            Triple(0f,PHI, 1f/PHI),
            Triple(0f,PHI,-1f/PHI),
            Triple(0f,-PHI, 1f/PHI),
            Triple(0f,-PHI,-1f/PHI),
            Triple(PHI, 1f/PHI,0f),
            Triple(PHI,-1f/PHI,0f),
            Triple(-PHI, 1f/PHI,0f),
            Triple(-PHI,-1f/PHI,0f),
            Triple(1f/PHI,0f,PHI),
            Triple(1f/PHI,0f,-PHI),
            Triple(-1f/PHI,0f,PHI),
            Triple(-1f/PHI,0f,-PHI)
        )
        val CIRCUMRATHIO  = sqrt(3.0).toFloat()
        val FACES = arrayOf(
            intArrayOf(8, 9, 1, 12, 0),
            intArrayOf(9, 5, 19, 17, 1),
            intArrayOf(5, 9, 8, 4, 14),
            intArrayOf(11, 10, 6, 15, 7),
            intArrayOf(11, 10, 2, 13, 3),
            intArrayOf(11, 7, 19, 17, 3),
            intArrayOf(10, 6, 18, 16, 2),
            intArrayOf(4, 8, 0, 16, 18),
            intArrayOf(14, 15, 6, 18, 4),
            intArrayOf(14, 15, 7, 19, 5),
            intArrayOf(12, 13, 3, 17, 1),
            intArrayOf(12, 13, 2, 16, 0)
        )
    }

    private val vertex = VERTEX_COORS.map {
        Triple(it.first + sketch.random(-1f,1f)/3,
            it.second + sketch.random(-1f,1f)/3,
            it.third + sketch.random(-1f,1f)/3)
    }

    private val rockShape: PShape = sketch.createShape(GROUP)
    private val color = Array(FACES.size) {sketch.random(80f, 180f)}

    private fun faceToShape(face: IntArray, color: Float): PShape{
        val shape = sketch.createShape()
        shape.beginShape()
        shape.fill(color)
        for(i in face){
            shape.vertex(vertex[i].first, vertex[i].second, vertex[i].third)
        }
        shape.endShape(CLOSE)
        return shape
    }
    init {
        FACES.zip(color).map { (f,c) -> faceToShape(f,c)}.forEach {rockShape.addChild(it)}
    }

    fun draw(){
        sketch.pushMatrix()
        sketch.scale(circumrathio / CIRCUMRATHIO)
        sketch.shape(rockShape)
        sketch.popMatrix()
    }
}

class Rocker: PApplet() {
    private lateinit var rock: Rock

    override fun settings() {
        size(800, 800, P3D)
    }

    override fun setup() {
        rock = Rock(this, 100)
        lights()
    }

    override fun draw() {
        background(220)
        translate(width / 2f, height / 2f)
        rotateX(frameCount * 0.01f)
        rotateY(frameCount * 0.01f)
        rotateZ(frameCount * 0.01f)
        rock.draw()
    }
}

fun main() {
    PApplet.runSketch(arrayOf("Rocker"), Rocker())
}