import java.lang.StringBuilder
import java.util.*

class MakeKey(val l:Int, val c:Int, val givenChars: CharArray) {
    val sb: StringBuilder = StringBuilder()
    val vowel = arrayOf('a', 'e', 'i', 'u', 'o')

    fun solveMK(startIndex: Int, consCnt:Int, vowelCnt:Int) {
        if (sb.length == l) {
            //자음이 최소 2개, 모음이 최소 1개인 조건을 만족하면 print
            if(consCnt >= 2 && vowelCnt >= 1)
                println(sb.toString())
            return
        }

        for (i in startIndex until c) {
            sb.append(givenChars[i])

            //알파벳을 추가해 가면서 솔루션을 찾음
            if(vowel.contains(givenChars[i]))
                solveMK(i + 1, consCnt, vowelCnt + 1)
           else
                solveMK(i + 1, consCnt + 1, vowelCnt)

            //현재 가장 뒤에 있는 알파벳을 지우고 다른 알파벳에 대해 솔루션을 찾음
            sb.deleteCharAt(sb.length - 1)
        }
        return
    }
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val charTreeSet = TreeSet<Char>()

    val l = sc.nextInt()
    val c = sc.nextInt()
    //알파벳을 정렬해서 삽입
    for (i in 1..c) {
        charTreeSet.add(sc.next()[0])
    }
    val obj = MakeKey(l, c, charTreeSet.toCharArray())
    obj.solveMK(0, 0, 0)
}