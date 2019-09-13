fun main() {
	val board = Board(8, 8)
	val bot = Bot(board)
	val (row, column) = getLocation()
	board.mineBlock(row, column)
	bot.run()
}

fun getLocation(): Pair<Int, Int> {
	try {
		print("Row: ")
		val row = readLine()?.toInt()
		print("\nColumn: ")
		val column = readLine()?.toInt()
		print("\n")
		if (row == null || column == null) {
			return getLocation()
		}
		return Pair(row, column)
	} catch (err: NumberFormatException) {
		return getLocation()
	}
}
