typealias Window = Triple<Int, Int, Int>

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { it.toInt() }
            .zipWithNext()
            .count { it.second > it.first }
    }

    fun part2(input: List<String>): Int {
        val depths = input.map { it.toInt() }
        val windows = mutableListOf<Window>()

        for (i in 2..depths.lastIndex) {
            val window = Window(depths[i - 2], depths[i - 1], depths[i])
            windows.add(window)
        }

        val windowSums = windows.map { it.first + it.second + it.third }

        var increases = 0

        for (i in 0 until windowSums.lastIndex) {
            if (windowSums[i + 1] > windowSums[i]) {
                increases++
            }
        }

        return increases
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))

    check(part2(testInput) == 5)
    println(part2(input))
}
