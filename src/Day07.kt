import kotlin.math.abs

fun main() {
    fun part1(input: List<Int>): Int {
        val sorted = input.sorted()

        val median = with(sorted) {
            if (size % 2 != 0) {
                this[size / 2]
            } else {
                (this[((size - 1) / 2)] + this[(size / 2)]) / 2
            }
        }

        return sorted.sumOf { abs(median - it) }
    }

    fun part2(input: List<Int>): Int {
        return input.sorted().indices.minOf { pos ->
            input.sumOf {
                val distance = abs(it - pos)
                distance * (distance + 1) / 2
            }
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readCsvNumbers("Day07_test")
    check(part1(testInput) == 37)

    val input = readCsvNumbers("Day07")
    println(part1(input))

    check(part2(testInput) == 168)
    println(part2(input))
}
