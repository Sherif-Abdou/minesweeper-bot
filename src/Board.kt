import com.sun.org.apache.xpath.internal.operations.Bool
import kotlin.math.ceil
import kotlin.random.Random

data class Vector(val x: Int, val y: Int)

class Board(val width: Int, val height: Int) {

	var map = Array<Array<Block>>(width) { x -> Array<Block>(height) { y -> Block(x, y, false) } }
	var isPlaying = true

	private val random = Random(70)

	private var directions = mutableListOf<Vector>()

	init {
		val amountOfMines = ceil((((width + height) / 2.0) * ((width + height) / 2.0)) / 5.0).toInt()
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

	init {
		directions.add(Vector(1, -1))
		directions.add(Vector(1, 0))
		directions.add(Vector(1, 1))
		directions.add(Vector(-1, 1))
		directions.add(Vector(-1, 0))
		directions.add(Vector(-1, -1))
		directions.add(Vector(0, 1))
		directions.add(Vector(0, -1))
	}

	fun findNeighboringBlocks(x: Int, y: Int): MutableList<Block> {
		val blocks = mutableListOf<Block>()
		for (direction in this.directions) {
			val newPosition = Vector(x + direction.x, y + direction.y)
			if (boundWidth(newPosition.x) && boundHeight(newPosition.y)) {
				blocks.add(map[newPosition.x][newPosition.y])
			}
		}
		return blocks
	}

	private fun boundWidth(value: Int): Boolean {
		return when {
			value < 0 -> false
			value >= width -> false
			else -> true
		}
	}

	private fun boundHeight(value: Int): Boolean {
		return when {
			value < 0 -> false
			value >= height -> false
			else -> true
		}
	}

	fun flat(): MutableList<Block> {
		val blocks = mutableListOf<Block>()

		for (layer in map) {
			for (block in layer) {
				blocks.add(block)
			}
		}

		return blocks
	}

	fun findBorderBlocks(): MutableList<Block> {
		val blocks = mutableListOf<Block>()

		for (block in flat()) {
			if (block.isVisible) {
				val neighbors = this.findNeighboringBlocks(block.x, block.y).filter { neighbor -> !neighbor.isVisible }
				if (neighbors.isNotEmpty()) {
					blocks.add(block)
				}
			}
		}

		return blocks
	}

	fun borderNumber(x: Int, y: Int): Int {
		val neighbors = findNeighboringBlocks(x, y)
		val mines = neighbors.filter { block -> block.isMine }
		return mines.size
	}

	fun mineBlock(x: Int, y: Int, noRecursion: Boolean = false): Boolean {
		val block = map[x][y]
		if (block.isMine && !block.isFlagged) {
			isPlaying = false
			return isPlaying
		} else {
			block.isVisible = true
			if (!noRecursion) {
				for (neighbor in findNeighboringBlocks(block.x, block.y)) {
					if (!neighbor.isMine && !neighbor.isVisible) {
						val nmines = findNeighboringBlocks(neighbor.x, neighbor.y).filter { b -> b.isMine && !b.isFlagged }
						if (nmines.isEmpty()) mineBlock(neighbor.x, neighbor.y)
						else mineBlock(neighbor.x, neighbor.y, noRecursion = true)
					}
				}
			}
		}
		return isPlaying
	}

	override fun toString(): String {
		var str = ""
		for (layer in map) {
			for (block in layer) {
				str = when {
					block.isFlagged -> str.plus("F")
					block.isVisible -> str.plus("_")
					block.isMine -> str.plus("X")
					else -> str.plus("|")
				}
				str = str.plus(" ")
			}
			str = str.plus("\n")
		}
		return str
	}

	fun hasWon(): Boolean {
		if (!isPlaying) {
			return false
		}
		for (block in flat()) {
			if (!block.isVisible && !block.isFlagged) {
				return false
			}
		}
		return true
	}
}
