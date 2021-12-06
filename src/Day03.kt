fun main() {
    fun part1(input: List<String>): Int {
        val length = input.first().length

        val sb = StringBuilder()

        for (i in 0 until length) {
            val (ones, zeroes) = input.map { it[i] }.partition { it == '1' }
            sb.append(if (ones.size > zeroes.size) '1' else '0')
        }

        val binaryString = sb.toString()

        val gammaRate = binaryString.toInt(2)
        val epsilonRate = binaryString.replace('1', '2')
            .replace('0', '1')
            .replace('2', '0')
            .toInt(2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        fun findValue(remaining: List<String>, i: Int, p: (Int, Int) -> Boolean): String {
            if (remaining.size == 1) {
                return remaining.first()
            }

            val (ones, zeroes) = remaining.partition { it[i] == '1' }
            val newRemaining = if (p(ones.size, zeroes.size)) ones else zeroes

            return findValue(newRemaining, i + 1, p)
        }

        val oxygenGeneratorValue = findValue(input, 0) { x, y -> x >= y }.toInt(2)
        val carbonDioxideScrubberRating = findValue(input, 0) { x, y -> x < y }.toInt(2)

        return oxygenGeneratorValue * carbonDioxideScrubberRating
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))

    check(part2(testInput) == 230)
    println(part2(input))
}
