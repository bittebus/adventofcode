package advent.of.code

import java.io.File

class ThirdDay {
    fun run1(list: List<CharArray>) {
        //ok rules are, one down three to the right
        var max = list.size - 1
//        var down = 0
        var right = 3
        var trees = 0
        for (down in 1..max) {
            val char = list[down][right]
            if (char == '#') trees++
            if (char == '#') println("$down down, $right right char = $char")
            right += 3
            if (right >= list[down].size) right -= list[down].size
        }
        //trees 299
        println("trees $trees")
    }

    fun run2(list: List<CharArray>, rightSteps: Int, downSteps: Int, max: Int): Long {
        var right = 0
        var trees = 0L
        for (down in 1..max) {
            if (down % downSteps == 0) {
                right += rightSteps
                if (right >= list[down].size) right -= list[down].size
                val char = list[down][right]
                if (char == '#') trees++
            }
        }
        return trees
    }


    fun run1b(original: List<CharArray>) {
        //need to skip first
        val list = original.subList(1, original.size - 1)
        println("orginal =  ${original.size} list=${list.size}")
        var position = 0;
        var down = 1;
        val found = list.map {
            position += 3
            if (position >= it.size) position -= it.size
            if (it[position] == '#') println("$down down, $position right char = ${it[position]}")
            down++
            it[position]
        }.filter { it == '#' }
        println("found = ${found.size}")
    }
}

//found = 67
//found = 298
fun main() {
    val thirdDay = ThirdDay()
    val url = thirdDay::class.java.getResource("/dayThree.txt")
    val fileLines = File(url.file).bufferedReader().readLines()

    val charArrays: List<CharArray> = fileLines.map { it.toCharArray() }
    val max = charArrays.size - 1
    val now = System.nanoTime()
    val first = thirdDay.run2(charArrays, 3, 1, max)
    val afterFirstRun = System.nanoTime()
    val trees1 = thirdDay.run2(charArrays, 1, 1, max)
    val trees2 = thirdDay.run2(charArrays, 3, 1, max)
    val trees3 = thirdDay.run2(charArrays, 5, 1, max)
    val trees4 = thirdDay.run2(charArrays, 7, 1, max)
    val trees5 = thirdDay.run2(charArrays, 1, 2, max)
    val firstTime = (afterFirstRun-now)/1000
    val time = (System.nanoTime()-afterFirstRun)/1000
    println("first took $firstTime microSeconds")
    println("second took $time microSeconds")
    println(trees1)
    println(trees2)
    println(trees3)
    println(trees4)
    println(trees5)


//    val total = trees1 * trees2 * trees3 * trees4 * trees5
//    println("The total=$total")
    println("total = ${trees1 * trees2 * trees3 * trees4 * trees5}")
//The total=-673682018
//total = -673682018
}