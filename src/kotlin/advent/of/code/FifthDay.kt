package advent.of.code

import advent.Util.Companion.fileToStringList
import kotlin.system.measureNanoTime

class FifthDay {

    private fun half(h: Holder, char: Char): Holder {
        val half = (h.rowMax - h.rowMin) / 2 + 1
        val columnHalf = (h.columnMax - h.columnMin) / 2 + 1
        return when (char) {
            'F' -> Holder(rowMin = h.rowMin, rowMax = h.rowMax - half, h.columnMin, h.columnMax, h.seat)
            'B' -> Holder(rowMin = h.rowMin + half, rowMax = h.rowMax, h.columnMin, h.columnMax, h.seat)
            'R' -> Holder(h.rowMin, h.rowMax, h.columnMin + columnHalf, h.columnMax, h.seat)
            'L' -> Holder(h.rowMin, h.rowMax, h.columnMin, h.columnMax - columnHalf, h.seat)
            else -> error("neither F, B, R nor L")
        }
    }

    private fun createHolder(value: String): Holder {
        var holder = Holder(0, 127, 0, 7, 0)
        value.toCharArray().map {
            holder = half(holder, it)
        }
        holder.seat = holder.rowMax * 8 + holder.columnMax
        return holder
    }

    fun first(list: List<String>): Int {
        val holders: List<Holder> = list.map {
            createHolder(it)
        }
        return holders.maxByOrNull { it.seat }?.seat!!
    }


    fun second(list: List<String>): Int {
        val holders: List<Holder> = list.map {
            createHolder(it)
        }
        val minSeat = holders.minByOrNull { it.seat }?.seat!!
        val sorted = holders.sortedBy { it.seat }.map { it.seat - minSeat + 1 }.toIntArray()
        val missing = getMissingSeat(sorted, sorted.size)

        return missing + minSeat - 1
    }


    private fun getMissingSeat(a: IntArray, n: Int): Int {
        var total: Int = (n + 1) * (n + 2) / 2
        var i = 0
        while (i < n) {
            total -= a[i]
            i++
        }
        return total
    }
}

data class Holder(val rowMin: Int, val rowMax: Int, val columnMin: Int, val columnMax: Int, var seat: Int)

//maxSeat 855 missing 552
fun main() {
    val fifth = FifthDay()
    var seat: Int
    val list = fileToStringList("/fifth.txt")

    val m1 = measureNanoTime {
        seat = fifth.first(list)
    }
    println("maxSeat = $seat, ${m1 / 1000} micros ")
    val m2 = measureNanoTime {
        seat = FifthDay().second(list)
    }
    println("missing seat $seat, ${m2 / 1000} micros")
}

