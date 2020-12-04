import java.io.File
import java.io.InputStream
import java.util.regex.Matcher
import java.util.regex.Pattern

class Util {
    companion object {
        fun readFileLines(pathName: String): IntArray {
            val url = this::class.java.getResource(pathName)
            val inputStream: InputStream = File(url.file).inputStream()
            val lineList = mutableListOf<Int>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }
            return lineList.toIntArray()
        }
    }
}

fun main(){

    var subjectString = "341e13"
    val regex: Pattern = Pattern.compile("#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})\$")
    val found = regex.matcher(subjectString).find()
    println("found $found")
    val regexMatcher: Matcher = regex.matcher(subjectString)
    if (regexMatcher.find()) {
        println("found a match")
    }


}
