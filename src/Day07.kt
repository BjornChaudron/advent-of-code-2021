import kotlin.math.abs

fun main() {
    fun part1(input: List<Int>): Int {
        val medianIndex = input.size / 2
        val median = input.sorted()[medianIndex]

        return input.sumOf { abs(median - it) }
    }

    fun part2(input: List<Int>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readCsvNumbers("Day07_test")
    check(part1(testInput) == 37)

    val input = readCsvNumbers("Day07")
    println(part1(input))

    check(part2(testInput) == 1)
    println(part2(input))
}
