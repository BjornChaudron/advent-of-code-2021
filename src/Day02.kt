data class Coordinates(val x: Int = 0, val y: Int = 0)
data class Movement(val direction: String, val delta: Int)

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            val (direction, delta) = line.split(" ")
            Movement(direction, delta.toInt())
        }.fold(Coordinates()) { acc, next ->
            when (next.direction) {
                "forward" -> acc.copy(x = acc.x + next.delta)
                "up" -> acc.copy(y = acc.y - next.delta)
                "down" -> acc.copy(y = acc.y + next.delta)
                else -> error("Unknown movement")
            }
        }.run { x * y }
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)

    val input = readInput("Day02")
    println(part1(input))

    check(part2(testInput) == 1)
    println(part2(input))
}
