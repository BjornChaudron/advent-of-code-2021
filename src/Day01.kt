fun main() {
    fun part1(input: List<String>): Int {
        val depths = input.map { it.toInt() }
        var increases = 0

        for (i in 0 until depths.lastIndex) {
            if (depths[i + 1] > depths[i]) {
                increases++
            }
        }

        return increases
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
