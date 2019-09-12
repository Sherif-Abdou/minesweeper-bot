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

fun testBorder() {
	val board = Board(8, 8)
	board.mineBlock(0, 0)
	val blocks = board.findBorderBlocks()
	for (block in blocks) {
		println("X: ${block.x} Y: ${block.y} Mine: ${block.isMine}")
	}
}

fun testPrint() {
	val board = Board(8, 8)
	board.mineBlock(0, 0)
	print(board.toString())
	print("\n ${board.borderNumber(2, 0)}")
}

fun main() {
//	testBoard()
//	testNeighbor()
//	testBorder()
	testPrint()

	val board = Board(8, 8)
	board.mineBlock(0, 0)
	for (block in board.flat().filter { f -> f.isMine }) {
		println("X: ${block.x} Y: ${block.y} Mine: ${block.isMine}")
	}
}
