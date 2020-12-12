package advent.of.code

import advent.Util
import kotlin.time.measureTimedValue


@kotlin.time.ExperimentalTime
fun main() {
    val day = SeventhDay()
    val list = Util.fileToStringList("/7.txt")
    println(list.size)
    val first = measureTimedValue {
        day.part1(list)
    }
    val second = measureTimedValue {
        day.part2(list)
    }
    println("first:$first second:$second")
}

class SeventhDay {
    fun part1(list: List<String>): Int {

        val bagTypes = list.map {
            val k = it.split(" ")
            "${k[0]} ${k[1]} "
        }.toList()
        println("total types of bags = ${bagTypes.size}")


        var gj = mutableSetOf("shiny gold")
        val total = mutableSetOf<String>()
        do {
            gj = find(list, gj)
            total.addAll(gj)
        } while (gj.isNotEmpty())
        return total.size
    }

    fun part2(files: List<String>):Int {
        files.sortedBy { it.length }
        var loop = files.toMutableSet()
        val endMaps = mutableMapOf<String, Int>()
        val map = mutableMapOf<String, Int>()
        files.filter { it.contains("contain no other bags") }.map {
            val nameSplit = it.trim().split(" ")
            val name = "${nameSplit[0]} ${nameSplit[1]}"
            endMaps.put(name, 0)
        }
        return create(loop, endMaps)
    }

    fun create(looping: MutableSet<String>, map: MutableMap<String, Int>): Int {
        var loop = looping.toMutableSet()
        do {
            val newLoop = mutableSetOf<String>()
            for (full in loop) {
                val nameSplit = full.trim().split(" ")
                val name = "${nameSplit[0]} ${nameSplit[1]}"
                if (!map.containsKey(name)) {
                    if (full.contains("contain no other bags.")) {
                        map[name] = 0
                    } else {
                        val res: Int = getChildren(full, map)
                        if (res == -1) {
                            newLoop.add(full)
                        } else {
                            map[name] = res
                            if (name == "shiny gold") {
                                return res
                            }
                        }
                    }
                }
            }
            loop = newLoop
        } while (newLoop.isNotEmpty())
        return map["shiny gold"] ?: -1
    }

    fun create2(looping: MutableSet<String>, map: MutableMap<String, Int>): Int {
        var loop = looping.toMutableSet()
        do {
            val newLoop = mutableSetOf<String>()

            for ((index, full) in loop.withIndex()) {
                val nameSplit = full.trim().split(" ")
                val name = "${nameSplit[0]} ${nameSplit[1]}"
                if (map.containsKey(name)) {
                    //already done
                } else if (full.contains("contain no other bags.")) {
                    map[name] = 0
                } else {
                    val res: Int = getChildren(full, map)
                    if (res == -1) {
                        newLoop.add(full)
                    } else {
                        map[name] = res
                        if (name == "shiny gold") {
                            return res
                        }
                    }
                }
            }
            loop = newLoop
        } while (newLoop.isNotEmpty())
        return map["shiny gold"] ?: -1
    }

    fun getChildren(full: String, map: MutableMap<String, Int>): Int {
        val bags = full.substring(full.indexOf("contain") + 8).split(",")
        val tmp = mutableMapOf<String, Int>()
        var total = 0
        var found = 0
        bags.map {
            val split = it.trim().split(" ")
            val num = split[0].toInt()
            val name = "${split[1]} ${split[2]}"
            if (!map.containsKey(name)) {
                return -1
            } else {
                total += if (map[name] == 0) num else num + num * map[name]!!
                found++
            }
        }
        if (bags.size == found) {
            return total
        }
        return -1
    }


    private fun find(list: List<String>, lookFor: Set<String>): MutableSet<String> {
        val gj = mutableSetOf<String>()
        val other = list.map { outer ->
            lookFor.map {
                if (!outer.startsWith(it) && outer.contains(it)) {
                    val k = outer.split(" ")
                    gj.add("${k[0]} ${k[1]} ")
                }
            }
        }
        return gj
    }
}

