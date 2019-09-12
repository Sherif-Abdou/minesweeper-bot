import kotlin.math.ceil
import kotlin.random.Random

data class Position(val x: Int, val y: Int)

class Board(val width: Int, val height: Int) {
	var map = Array<Array<Block>>(width) {x -> Array<Block>(height) {y -> Block(x, y, false)}}
	private val random = Random(1)
	init {
		val amountOfMines = ceil((((width + height) / 2.0) * ((width + height)/2.0)) / 10.0).toInt()
		for (i in 0..amountOfMines) {
			generateMine()
		}
	}

	private fun generateMine() {

		val randomX = random.nextInt(0, width)
		val randomY = random.nextInt(0, height)
		if (!map[randomX][randomY].isMine) {
			map[randomX][randomY].isMine = true
		} else generateMine()
	}
}
