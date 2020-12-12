package advent.of.code

import advent.Util.Companion.fileToStringList
import advent.of.dto.Passport
import kotlin.system.measureNanoTime

class FourthDay {

    fun first(fileLines: List<String>) {
        var passport = Passport()
        val passports = mutableListOf<Passport>()
        fileLines.map { line ->
            if (line.isEmpty()) {
                passports.add(passport)
                passport = Passport()
            } else {
                passport.setValues(line)
            }
        }
        passports.add(passport)
        println("Valid passports 1 ${passports.filter { it.isValid() }.size}")
        println("Valid passports 2 ${passports.filter { it.isStrictValid() }.size}")
    }
}

fun main() {
    val fourthDay = FourthDay()
    val list = fileToStringList("/dayFour.txt")
    val timeInNanos = measureNanoTime {
        fourthDay.first(list)
    }
    println("$timeInNanos nanos ${timeInNanos/1000} micros ${timeInNanos/1000000} millis")
}