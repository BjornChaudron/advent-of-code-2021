import java.lang.Integer.max

fun main() {
    fun part1(input: List<String>): Int {
        val length = input.first().length

        var binaryString = ""

        for (i in 0 until length) {
            val (ones, zeroes) = input.map { it[i] }.partition { it == '1' }
            binaryString += if (ones.size > zeroes.size) '1' else '0'
        }

        val gammaRate = binaryString.toInt(2)
        val epsilonRate = binaryString.replace('1', '2')
            .replace('0', '1')
            .replace('2', '0')
            .toInt(2)

        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)

    val input = readInput("Day03")
    println(part1(input))

    check(part2(testInput) == 1)
    println(part2(input))
}
