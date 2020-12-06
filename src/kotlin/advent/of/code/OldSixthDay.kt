package advent.of.code

import advent.Util.Companion.fileToStringList
import kotlin.system.measureNanoTime

fun main() {
    val list:List<String>
    val readingFile = measureNanoTime {
        list = fileToStringList("/daySix.txt")
    }
    println("reading file took ${readingFile/1000}")
    var set = mutableSetOf<Char>()
    var answer = 0

    //Part 1
    val start = System.nanoTime()
    list.map {
        if (it.isEmpty()) {
            answer += set.size
            set = mutableSetOf()
        } else {
            set.addAll(it.toCharArray().toSet())
        }
    }
    //dont forget the last one
    answer += set.size

    val endFirst = System.nanoTime()

    //Part 2
    var groupAnswers = ""
    var membersOfGroup = 0
    var yesSum = 0
    val startSecond = System.nanoTime()
    list.map { string ->
        if (string.isEmpty()) {
            //reached the end of a goup
            yesSum += groupAnswers.groupBy { it }.values.filter {
                    value -> value.size == membersOfGroup }.size
            //reset
            groupAnswers = ""
            membersOfGroup = 0
        } else {
            membersOfGroup += 1
            groupAnswers += string
        }
    }

    //Dont forget last value
    yesSum += groupAnswers.groupBy { it }.values.filter { value -> value.size == membersOfGroup }.size
    val endSecond = System.nanoTime()

    val firstNanos = endFirst - start
    val secondNanos = endSecond - startSecond

    println("Part 1: Answer $answer Time: ${firstNanos/1000} micros")
    println("Part 2: Answer $yesSum Time: ${secondNanos/1000} micros")
}