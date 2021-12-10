fun main() {
    fun part1(input: List<String>): Int {
        return playBingoGame(input).first().let { it.first.getSumOfUnmarked() * it.second }
    }

    fun part2(input: List<String>): Int {
        return playBingoGame(input).last().let { it.first.getSumOfUnmarked() * it.second }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)

    val input = readInput("Day04")
    println(part1(input))

    check(part2(testInput) == 1924)
    println(part2(input))
}

fun playBingoGame(input: List<String>): List<Pair<Board, Int>> {
    val boardParser = BoardParser()

    val turns = boardParser.parseTurns(input)
    val boards = boardParser.parseBoards(input)

    val remainingBoards = boards.toMutableList()
    val bingoBoards = mutableListOf<Pair<Board, Int>>()

    turns.forEach { turn ->
        remainingBoards.forEach { b -> b.mark(turn) }

        if (remainingBoards.any { it.hasBingo() }) {
            remainingBoards.filter { it.hasBingo() }
                .let {
                    remainingBoards.removeAll(it)
                    it
                }.map { Pair(it, turn) }
                .let { bingoBoards.addAll(it) }
        }
    }

    return bingoBoards
}

class BoardParser {

    fun parseTurns(input: List<String>): List<Int> {
        return input.first().split(",").map { it.toInt() }
    }

    fun parseBoards(input: List<String>): List<Board> {
        val boards = mutableListOf<Board>()

        fun parseBoard(i: Int): Board {
            val startLineNr = i + 1
            val endLineNr = i + 6
            val numbers = input.subList(startLineNr, endLineNr)
                .map { line ->
                    line.trim()
                        .split("""\s+""".toRegex())
                        .map { it.toInt() }
                        .toTypedArray()
                }.toTypedArray()

            return Board(numbers)
        }

        for (i in 1..input.lastIndex step 6) {
            val board = parseBoard(i)
            boards.add(board)
        }

        return boards
    }
}

data class Cell(val x: Int, val y: Int)
data class Entry(val number: Int, var isMarked: Boolean = false)

fun Int.toIndex() = this - 1

class Board(numbers: Array<Array<Int>>) {
    private val width = numbers.size
    var cells: Array<Array<Cell>> = arrayOf()
    private var state: MutableMap<Cell, Entry> = mutableMapOf()
    private var index: MutableMap<Int, Cell> = mutableMapOf()

    init {
        numbers.forEachIndexed { rowIndex, row ->
            var cellRow: Array<Cell> = arrayOf()

            row.forEachIndexed { columnIndex, number ->
                val cell = Cell(rowIndex, columnIndex)
                cellRow += cell
                state[cell] = Entry(number)
                index[number] = cell
            }

            cells += cellRow
        }
    }

    fun mark(number: Int) {
        index[number]?.let { state[it]?.isMarked = true }
    }

    fun getSumOfUnmarked(): Int {
        return state.asSequence()
            .filter { (_, v) -> !v.isMarked }
            .map { (_, v) -> v }
            .fold(0) { acc, next -> acc + next.number }
    }

    fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        return jRange.asSequence()
            .filter { j -> j in 1..width }
            .map { j -> cells[i.toIndex()][j.toIndex()] }
            .toList()
    }

    fun getColumn(iRange: IntRange, j: Int): List<Cell> {
        return iRange.asSequence()
            .filter { i -> i in 1..width }
            .map { i -> cells[i.toIndex()][j.toIndex()] }
            .toList()
    }

    fun get(cell: Cell): Entry {
        return state[cell]!!
    }

    fun hasBingo() = hasHorizontalBingo() || hasVerticalBingo()

    private fun hasHorizontalBingo(): Boolean {
        return (1..width).asSequence()
            .map { rowIndex -> getRow(rowIndex, 1..width) }
            .map { row -> row.map { cell -> get(cell) } }
            .any { row -> row.all { it.isMarked } }
    }

    private fun hasVerticalBingo(): Boolean {
        return (1..width).asSequence()
            .map { columnIndex -> getColumn(1..width, columnIndex) }
            .map { row -> row.map { cell -> get(cell) } }
            .any { row -> row.all { it.isMarked } }
    }
}