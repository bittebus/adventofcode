package advent.of.code

import advent.Util
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.system.measureNanoTime

class SixthDay {


    fun partOne(list: List<String>): Int {
        var answer = 0
        val nlChar = System.lineSeparator()[0]
        list.map { string ->
            answer += string.toCharArray().filter { it != nlChar }.toSet().size
        }
        return answer
    }


    fun partTwo(list: List<String>): Int {
        var groupAnswers = ""
        var membersOfGroup = 0
        var yesSum = 0
        list.map { string ->
            if (string.isEmpty()) {
                //reached the end of a group
                yesSum += groupAnswers.groupBy { it }.values.filter { value -> value.size == membersOfGroup }.size
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
        return yesSum
    }
}

fun main() {
    var list = Util.fileSplitOnNewLine("/daySix.txt")
    val sixthDay = SixthDay()
    var answer: Int
    var yesSum: Int

    val mnt2 = measureNanoTime {
        answer = sixthDay.partOne(list)
    }

    println("part 1 answer:$answer new time:$mnt2 old time:34894552 new takes ${BigDecimal((mnt2.toDouble() /34894552.0 )*100).setScale(2, RoundingMode.HALF_EVEN)}% of old to run")


    list = Util.fileToStringList("/daySix.txt")
    val mnt4 = measureNanoTime {
        yesSum = sixthDay.partTwo(list)
    }
    println("part 2 answer=$yesSum time=${mnt4 / 1000} compare to:7060 new takes ${BigDecimal((mnt4.toDouble() /7515455.0 )*100).setScale(2, RoundingMode.HALF_EVEN)}% of old run")

}