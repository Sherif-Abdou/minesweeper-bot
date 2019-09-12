class Bot(var board: Board) {
	fun run() {
		mineGiven()
	}

	private fun mineGiven() {
		val borderBlocks = board.findBorderBlocks()
		for (block in borderBlocks) {
			val amountOfMines = board.borderNumber(block.x, block.y)
			val invisibleNeighbors = board.findNeighboringBlocks(block.x, block.y).filter { neighbor -> !neighbor.isVisible }
			if (amountOfMines == invisibleNeighbors.size) {
				for (neighbor in invisibleNeighbors) {
					neighbor.isFlagged = true
				}
			}
		}
	}
}
