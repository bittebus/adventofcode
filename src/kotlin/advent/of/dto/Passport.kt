package advent.of.dto

import java.util.regex.Pattern


class Passport() {

    private val regex: Pattern = Pattern.compile("#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$")
    private val eyeColors = listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")

    private var byr: String? = null// (Birth Year)                  byr (Birth Year) - four digits; at least 1920 and at most 2002.
    private var iyr: String? = null// (Issue Year)                  iyr (Issue Year) - four digits; at least 2010 and at most 2020.
    private var eyr: String? = null// (Expiration Year)             eyr (Expiration Year) - four digits; at least 2020 and at most 2030.
    private var hgt: String? = null// (Height)                      hgt (Height) - a number followed by either cm or in:

    // If cm, the number must be at least 150 and at most 193.
    // If in, the number must be at least 59 and at most 76.
    private var hcl: String? = null// (Hair Color)                  hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
    private var ecl: String? = null// (Eye Color)                   ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
    private var pid: String? = null// (Passport ID)                 pid (Passport ID) - a nine-digit number, including leading zeroes.
    private var cid: String? = null// (Country ID) not neccessary   cid (Country ID) - ignored, missing or not.

    fun isStrictValid(): Boolean {

        return checkRange(byr, 1920, 2002) &&
                checkRange(iyr, 2010, 2020) &&
                checkRange(eyr, 2020, 2030) &&
                checkHeight(hgt) &&
                checkHairColor(hcl) &&
                checkEyeColor(ecl) &&
                checkPid(pid)
    }

    fun checkPid(value: String?): Boolean {
        if (value == null) return false
        return try {
            value.toInt()
            value.length == 9

        } catch (e: Exception) {
            false
        }
    }

    private fun checkEyeColor(value: String?): Boolean {
        if (value == null) return false
        return eyeColors.find { it == value } != null
    }

    private fun checkHairColor(value: String?): Boolean {

        if (value == null) {
            return false
        }

        val b = regex.matcher(value).find()
        return b
    }

    private fun checkHeight(value: String?): Boolean {
        if (value == null) return false
        return try {
            val number = value.substring(0, value.length - 2)
            return when (value.substring(value.length - 2)) {
                "in" -> checkRange(number, 29, 76)
                "cm" -> checkRange(number, 150, 193)
                else -> false
            }
        } catch (e: Exception) {
            false
        }
    }

    private fun checkRange(value: String?, min: Int, max: Int): Boolean {
        if (value == null) return false
        try {
            if (value.toInt() in min..max) {
                return true
            }
            return false
        } catch (e: Exception) {
            println("value not int $value")
            return false
        }
    }


    fun isValid() = byr != null &&
            iyr != null &&
            eyr != null &&
            hgt != null &&
            hcl != null &&
            ecl != null &&
            pid != null


    fun setValues(fullText: String) {
        fullText.split(" ").map { text ->
            if (text.startsWith("byr", true)) byr = text.split(":")[1]
            if (text.startsWith("iyr", true)) iyr = text.split(":")[1]
            if (text.startsWith("eyr", true)) eyr = text.split(":")[1]
            if (text.startsWith("hgt", true)) hgt = text.split(":")[1]
            if (text.startsWith("hcl", true)) hcl = text.split(":")[1]
            if (text.startsWith("ecl", true)) ecl = text.split(":")[1]
            if (text.startsWith("pid", true)) pid = text.split(":")[1]
            if (text.startsWith("cid", true)) cid = text.split(":")[1]
        }
    }

    override fun toString(): String {
        return "byr: $byr iyr: $iyr eyr: $eyr hgt: $hgt hcl: $hcl ecl: $ecl pid: $pid cid: $cid"
    }
}