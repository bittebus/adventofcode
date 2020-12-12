package advent.of.code

import java.io.File
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class SecondDay {
    fun run1(fileLines: List<String>) {
        val start = System.currentTimeMillis()
        var counter = 0;
        var nomatch = 0;
        fileLines.map { line ->
            val split = line.split(" ")
            val minmax = split[0].split("-")
            val min = minmax[0].toInt()
            val max = minmax[1].toInt()
            val find = split[1].trim().substring(0, 1).single()
            var match = false;
            val found = split[2].trim().toCharArray().filter { it == find }.size
            if (found in min..max) {
                counter++
                match = true
            } else {
                nomatch++
                match = false
            }
//            println("min=$min, max=$max, find=$find found=$found, match=$match, looking in ${split[2]}")
        }
//        println("counter=$counter nomatch=$nomatch sum=${counter+nomatch} filelines=${fileLines.size}")
        val time = System.currentTimeMillis() - start
        println("first question found=$counter took $time ms")
    }

    fun run2(fileLines: List<String>) {
        val start = System.currentTimeMillis()
        var match = 0;
        var nomatch = 0;
        var found = false;
        fileLines.map { line ->
            val split = line.split(" ")
            val charArray = split[2].trim().toCharArray()
            val minmax = split[0].split("-")
            //to make this number 0 based I subtract one
            val min = minmax[0].toInt() - 1
            val max = minmax[1].toInt() - 1
            val find = split[1].trim().substring(0, 1).single()
            val first = charArray[min] == find
            val second = charArray[max] == find

            if (first == second) {
                nomatch++
                found = false
            } else {
                match++
                found = true
            }
//            println("min=$min = ${charArray[min]}, max=$max => ${charArray[max]}, find=$find match=$found, looking in ${split[2]}, ")
        }
        var time = System.currentTimeMillis() - start
//        println("found=$match nomatch=$nomatch sum=${match+nomatch} filelines=${fileLines.size}")
        println("second question found=$match took $time ms")
    }
}

fun main() {
    val secondDay = SecondDay()
    val url = secondDay::class.java.getResource("/dayTwo.txt")
    val fileLines = File(url.file).bufferedReader().readLines()
    println("First run!")
    secondDay.run1(fileLines)
    secondDay.run2(fileLines)
    println()
    println("Second run!")
    secondDay.run1(fileLines)
    secondDay.run2(fileLines)
    println()
    println("Third run!")
    secondDay.run1(fileLines)
    secondDay.run2(fileLines)
    println()
    println()
    println("Flipped first run!")
    secondDay.run2(fileLines)
    secondDay.run1(fileLines)
    println()
    println("Flipped second run!")
    secondDay.run2(fileLines)
    secondDay.run1(fileLines)
    println()
    println("Flipped third run!")
    secondDay.run2(fileLines)
    secondDay.run1(fileLines)
    println()



}