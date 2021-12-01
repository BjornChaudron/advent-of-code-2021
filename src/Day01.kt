typealias Window = Triple<Int, Int, Int>

fun main() {
    fun part1(input: List<String>): Int {
        return input.asSequence()
            .map { it.toInt() }
            .zipWithNext()
            .count { it.second > it.first }
    }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toInt() }

        return (2..depths.lastIndex).asSequence()
            .map { index -> Window(depths[index - 2], depths[index - 1], depths[index]) }
            .map { window -> window.first + window.second + window.third }
            .zipWithNext()
            .count { it.second > it.first }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))

    check(part2(testInput) == 5)
    println(part2(input))
}
