fun testMineGiven() {
	val board = Board(8, 8)
	board.mineBlock(0, 0)
	val bot = Bot(board)
	bot.run()
	println(board.toString())
}

fun main() {
	testMineGiven()
}
