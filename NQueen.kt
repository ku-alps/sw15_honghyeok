import java.util.*

var cnt = 0

private fun isValid(board: Array<IntArray>, row: Int, col: Int, N: Int): Boolean {
    if (1 in board[row]) return false

    var i = row
    var j = col
    while (i >= 0 && j >= 0) {
        if (board[i][j] == 1) return false
        i--
        j--
    }

    i = row
    j = col
    while (i < N && j >= 0) {
        if (board[i][j] == 1) return false
        i++
        j--
    }
    return true
}

fun solveNQ(board: Array<IntArray>, col: Int, N: Int): Int {
    if (col == N) {
        return cnt++
    }

    for (i in 0 until N) {
        if (isValid(board, i, col, N)) {
            board[i][col] = 1

            solveNQ(board, col + 1, N)

            board[i][col] = 0
        }
    }
    return cnt
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val N: Int = sc.nextInt()
    val board = Array(N) { IntArray(N) }

    solveNQ(board, 0, N)
    print(cnt)
}
