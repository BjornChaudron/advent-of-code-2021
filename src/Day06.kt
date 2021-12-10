fun main() {
    fun part1(input: List<String>): Int {
        val fish = input.first().split(",").map { it.toInt() }
        val days = 80

        return simulateDays(fish, days)
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)

    val input = readInput("Day06")
    println(part1(input))

    check(part2(testInput) == 1)
    println(part2(input))
}

fun simulateDays(fish: List<Int>, days: Int): Int {
    var result = fish.toMutableList()

    (1..days).forEach { day ->
        val spawns = result.count { it == 0 }

        result = result.map { if (it > 0) it - 1 else 6 }.toMutableList()

        repeat(spawns) {
            result += 8
        }
    }

    return result.size
}