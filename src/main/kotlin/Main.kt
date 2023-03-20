import processing.core.PApplet

class Main(): PApplet() {
    private val squares = mutableListOf<MovingSquare>()
    private var i = 0
    override fun settings() {
        size(512, 512)
    }

    override fun setup() {
        background(0)
    }

    override fun draw() {
        if (i % 2 == 0) {
            squares.add(MovingSquare(this, i))
            if(squares.first().isFinished())
                squares.removeFirst()
        }
        i++
        squares.asReversed().forEach { square ->
            square.update()
            square.display()
        }
    }
}

fun main(args: Array<String>) {
    val main = Main()
    PApplet.runSketch(arrayOf("Main"), main)
}