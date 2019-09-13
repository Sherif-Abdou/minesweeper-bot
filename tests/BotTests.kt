fun testMineGiven() {
	val board = Board(8, 8)
	board.mineBlock(0, 0)
	val bot = Bot(board)
	bot.mineGiven()
	println(board.toString())
}

fun testMineSurrounding() {
	val board = Board(8, 8)
	board.mineBlock(0, 0)
	val bot = Bot(board)
	bot.mineGiven()
	bot.mineSurrounding(board.map[2][1])
	println(board.toString())
}

fun testMineAround() {
	val board = Board(8, 8)
	board.mineBlock(0, 0)
	val bot = Bot(board)
	bot.mineGiven()
	bot.lookForMarked(board.map[2][2])
	println(board.toString())
}

fun testRun() {
	val board = Board(8, 8)
	println(board)
	board.mineBlock(0, 0)
	val bot = Bot(board)
	bot.run()
}

fun main() {
//	testMineGiven()
//	testMineSurrounding()
//	testMineAround()
	testRun()
}
