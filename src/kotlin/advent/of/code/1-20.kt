package advent.of.code

import advent.Util
import kotlin.system.measureNanoTime

class FirstDay {

    fun calculateFirst(firstArray: IntArray, secondArray: IntArray): Int {
        firstArray.map { value ->
            val a = secondArray.filter { it + value == 2020 }
            if (a.isNotEmpty()) {
                return a[0] * value
            }
        }
        return 0
    }

    fun calculateSecond(firstArray: IntArray, secondArray: IntArray, thirdArray: IntArray): Int {

        firstArray.map { first ->
            secondArray.map { second ->
                if (first + second < 2020) {
                    val a = thirdArray.filter { it + first + second == 2020 }
                    if (a.isNotEmpty()) {
                        return a[0] * first * second
                    }
                }
            }
        }
        return 0
    }
}

fun main() {
    val firstDay = FirstDay()
    val list = Util.fileToIntArray("/dayOne.txt")
    var value1: Int
    var value2: Int
    val timeInNanos1 = measureNanoTime {
        value1 = firstDay.calculateFirst(list, list)
    }
    val timeInNanos2 = measureNanoTime {
        value2 = firstDay.calculateSecond(list, list, list)
    }
    println("first value $value1")
    println("second value $value2")

    println("first took $timeInNanos1 ns")
    println("second took $timeInNanos2 ns")
}
