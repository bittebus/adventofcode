package advent

import java.io.File
import java.io.InputStream

class Util {
    companion object {
        fun fileToIntArray(pathName: String): IntArray {
            val url = this::class.java.getResource(pathName)
            val inputStream: InputStream = File(url.file).inputStream()
            val lineList = mutableListOf<Int>()
            inputStream.bufferedReader().useLines { lines -> lines.forEach { lineList.add(it.toInt()) } }
            return lineList.toIntArray()
        }

        fun fileToStringList(pathName: String): List<String> {
            val url = Util::class.java.getResource(pathName)
            return File(url.file).readLines()
        }
    }
}

