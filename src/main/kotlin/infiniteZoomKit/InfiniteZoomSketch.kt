package infiniteZoomKit

import processing.core.PApplet

class InfiniteZoomSketch(private val factory: FactoryCreator, private val autoRotateCamera: Boolean = false): PApplet(){

    private val elements = mutableListOf<InfiniteZoomable>()
    private var cameraAngle = 0f
    private val CAMERA_DISTANCE = 500f
    private val CAMERA_SPEED = 0.005f

    override fun settings() {
        size(1024, 1024, P3D)
    }

    override fun setup() {
        background(100)
        lights()

        for (i in 0..10){
            elements.add(factory.produce(this, i))
        }
    }

    override fun draw() {
        if(elements.last().isVisibile())
            elements.add(factory.produce(this))
        if(elements.first().isDeletable())
            elements.removeFirst()


        if(autoRotateCamera){
            camera(
                sin(cameraAngle) * CAMERA_DISTANCE,
                -CAMERA_DISTANCE*3,
                cos(cameraAngle) * CAMERA_DISTANCE,
                0f, 0f, 0f,
                0f, 1f, 0f) // upX, upY, upZ

            cameraAngle += CAMERA_SPEED
        } else {
            // the camera follows the mouse
            camera(
                mouseX.toFloat(), -mouseY.toFloat(), 300f, // eyeX, eyeY, eyeZ
                0f, 0f, 0f,// centerX, centerY, centerZ
                0f, 1f, 0f) // upX, upY, upZ
        }

        background(100)

        // draw a very large rectangle, the field
        fill(255)
        val fieldSize = 100000f
        box(fieldSize, 0f, fieldSize)

        // draw the elements
        elements.forEach { it.displayWithScale() }
    }
}