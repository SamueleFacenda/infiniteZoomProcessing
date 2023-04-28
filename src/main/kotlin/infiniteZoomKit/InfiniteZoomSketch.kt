package infiniteZoomKit

import examples.components.Grid
import processing.core.PApplet

class InfiniteZoomSketch(
    private val factory: FactoryCreator,
    private val autoRotateCamera: Boolean = false,
    private val startNumberOfElements: Int = 10,
    private val showGrid: Boolean = false,
    private val framerate: Int = 60,
): PApplet(){

    private val elements = mutableListOf<InfiniteZoomable>()
    private var cameraAngle = 0f
    private val CAMERA_DISTANCE = 500f
    private val CAMERA_SPEED = 0.005f

    private val grid = mutableListOf<InfiniteZoomable>()
    private val lato = 1024
    private val screenHeight = 1824
    private val screenWidth = 2736

    override fun settings() {
        size(lato, lato, P3D)
        //fullScreen(P3D);
    }

    override fun setup() {
        frameRate(framerate.toFloat())
        surface.setTitle("Infinite camp fire")
        surface.setResizable(true)
        // move to the center of the screen
        surface.setLocation((screenWidth-lato)/2, (screenHeight-lato)/2)

        background(100)
        lights()

        initElementsAndGrid()
    }

    private fun initElementsAndGrid() {
        for (i in startNumberOfElements downTo 0) {
            elements.add(factory.produceObjectOnSketch(this, i))

            if (showGrid)
                grid.add(Grid(this, i))
        }
    }

    override fun draw() {
        updateElementsList()
        if(showGrid){
            updateGridList()
        }

        updateCamera()
        background(100)
        drawField()

        grid.forEach {it.drawWithScale()}
        elements.forEach { it.drawWithScale() }
    }

    private fun drawField() {
        // draw a very large rectangle, the field
        fill(255)
        val fieldSize = 100000f
        box(fieldSize, 0f, fieldSize)
    }

    private fun updateElementsList() {
        if(elements.last().isVisibile())
            elements.add(factory.produceObjectOnSketch(this))
        if(elements.first().isDeletable())
            elements.removeFirst()
    }

    private fun updateGridList() {
        if(grid.last().isVisibile())
            grid.add(Grid(this))
        if(grid.first().isDeletable())
            grid.removeFirst()
    }

    private fun updateCamera() {
        if(autoRotateCamera){
            updateCameraRotating()
        } else {
            updateCameraWithMousePosition()
        }
    }

    private fun updateCameraWithMousePosition() {
        val horizontalDistance = 300f * sin(mouseY / 300f)
        camera(
            horizontalDistance * sin(mouseX / 100f),
            -300f * cos(mouseY / 300f),
            horizontalDistance * cos(mouseX / 100f), // eyeX, eyeY, eyeZ
            0f, 0f, 0f,// centerX, centerY, centerZ
            0f, 1f, 0f
        )
    }

    private fun updateCameraRotating() {
        camera(
            sin(cameraAngle) * CAMERA_DISTANCE,
            -CAMERA_DISTANCE*3,
            cos(cameraAngle) * CAMERA_DISTANCE,
            0f, 0f, 0f,
            0f, 1f, 0f) // upX, upY, upZ

        cameraAngle += CAMERA_SPEED
    }

}