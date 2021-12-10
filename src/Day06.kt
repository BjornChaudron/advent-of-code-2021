fun main() {
    fun part1(input: List<String>): Long {
        val fish = parseInput(input)
        val days = 80

        return simulateDays(fish, days)
    }

    fun part2(input: List<String>): Long {
        val fish = parseInput(input)
        val days = 256

        return simulateDays(fish, days)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)

    val input = readInput("Day06")
    println(part1(input))

    check(part2(testInput) == 26984457539L)
    println(part2(input))
}

fun parseInput(input: List<String>) = input.first().split(",").map { it.toInt() }

fun simulateDays(fish: List<Int>, days: Int): Long {
    val generations = LongArray(9)
    fish.forEach { generations[it]++ }

    (1..days).forEach { _ ->
        val spawns = generations[0]

        for (i in 1..generations.lastIndex) {
            generations[i - 1] = generations[i]
        }

        generations[6] += spawns
        generations[8] = spawns
    }

    return generations.fold(0L) { acc, next -> acc + next }
}