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

fun testNeighbor() {
	val board = Board(8, 8)
	val blocks = board.findNeighboringBlocks(7, 7)
	for (block in blocks) {
		println("X: ${block.x} Y: ${block.y} Mine: ${block.isMine}")
	}
}

fun main() {
//	testBoard()
	testNeighbor()
}
