package 백준.Silver.S3

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val NOT_EMPTY = "1"
data class Assignment(var time: Int, val score: Int) {
    fun isCompleted() = time == 0
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))){
    val assignmentStack = ArrayDeque<Assignment>()
    val totalTime = readLine().toInt()
    var totalScore = 0
    for (curTime in 1..totalTime) {
        val st = StringTokenizer(readLine())
//        과제 추가
        if(st.nextToken() == NOT_EMPTY) {
            val score = st.nextToken().toInt()
            val time = st.nextToken().toInt()
            assignmentStack.addFirst(Assignment(time, score))
        }

        if(assignmentStack.isNotEmpty()) {
            val assignment = assignmentStack.first()
            assignment.time -= 1
            if(assignment.isCompleted()) {
                totalScore += assignment.score
                assignmentStack.removeFirst()
            }
        }
    }

    println(totalScore)
    close()
}