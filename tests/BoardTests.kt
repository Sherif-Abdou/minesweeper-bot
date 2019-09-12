import kotlin.test.*
import Board

fun testBoard() {
	val board = Board(8, 8)
	for (layer in board.map) {
		for (block in layer) {
			println("X: ${block.x} Y: ${block.y} Mine: ${block.isMine}")
		}
	}
}

fun main() {
	testBoard()
}
