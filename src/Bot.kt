class Bot(var board: Board) {
	fun run() {
		for (i in 0..2) {
			val mined = mineGiven()
			mined.forEach { block: Block -> lookForMarked(block) }
		}
	}

	fun mineGiven(): MutableList<Block> {
		val borderBlocks = board.findBorderBlocks()
		val minedBlocks = mutableListOf<Block>()
		for (block in borderBlocks) {
			val amountOfMines = board.borderNumber(block.x, block.y)
			val invisibleNeighbors = board.findNeighboringBlocks(block.x, block.y).filter { neighbor -> !neighbor.isVisible }
			if (amountOfMines == invisibleNeighbors.size) {
				for (neighbor in invisibleNeighbors) {
					neighbor.isFlagged = true
					minedBlocks.add(neighbor)
				}
			}
		}
		return minedBlocks
	}

	fun lookForMarked(block: Block) {
		val visibleNeighbors = board.findNeighboringBlocks(block.x, block.y).filter { neighbor -> neighbor.isVisible }
		for (neigbhor in visibleNeighbors) {
			checkAround(neigbhor)
		}
	}

	private fun checkAround(block: Block) {
		val mineNumbers = board.borderNumber(block.x, block.y)
		val flagged = board.findNeighboringBlocks(block.x, block.y).filter { neighbor -> neighbor.isFlagged }
		if (mineNumbers == flagged.size) {
			val unmined = board.findNeighboringBlocks(block.x, block.y).filter { neighbor -> !neighbor.isFlagged && !neighbor.isVisible }
			for (umine in unmined) {
				board.mineBlock(umine.x, umine.y)
			}
		}
	}

	fun mineSurrounding(block: Block) {
		val invisibleNeighbors = board.findNeighboringBlocks(block.x, block.y).filter { neighbor -> !neighbor.isVisible }
		for (neigbhor in invisibleNeighbors) {
			if (!neigbhor.isFlagged) {
				neigbhor.isVisible = true
			}
		}
	}
}
