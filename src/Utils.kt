import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = getFile(name).readLines()

private fun getFile(name: String) = File("src", "$name.txt")

fun readCsvNumbers(name: String) = readInput(name).first().split(",").map { it.toInt() }

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)
