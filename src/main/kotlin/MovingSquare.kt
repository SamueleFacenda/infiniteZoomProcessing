import processing.core.PApplet

class MovingSquare(
    private val sketch: PApplet,
    private val index: Int,
    ) {
    private var x = 0f
    private var y = 0f
    private var speed = 2f
    private var i = 0
    private val direction = (index/2)%4

    fun update() {
        when (direction) {
            1 -> {
                x += speed
            }
            2 -> {
                y += speed
                x += speed
            }
            3 -> {
                y += speed
            }
        }
        i += 1
    }

    fun display() {
        sketch.fill(index % 256)
        sketch.rect(x, y, sketch.width - i*speed, sketch.height - i*speed)
    }

    fun isFinished(): Boolean {
        return i*speed > sketch.width
    }
}