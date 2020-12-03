import java.io.File
import java.io.InputStream

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