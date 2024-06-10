package 백준.Silver.S1.p13335_트럭

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

data class Truck(val weight: Int, val time: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val bridgeLength = st.nextToken().toInt()
    val maxWeight = st.nextToken().toInt()

    var curWeight = 0
    var curTime = 0
    val truckQueue = ArrayDeque<Truck>()
    readLine().split(" ").asSequence().map(String::toInt).forEach {
//        시간 증가
        curTime++

//        truckQueue에서 다리를 건너간 트럭 제외
        while (truckQueue.isNotEmpty() && truckQueue.first().time + bridgeLength <= curTime) curWeight -= truckQueue.removeFirst().weight

//        현재 트럭이 다리에 올라갈 수 없으면 올라갈 수 있을 때까지 시간을 증가시킨다.
        while (truckQueue.isNotEmpty() && curWeight + it > maxWeight) {
            val (weight, time) = truckQueue.removeFirst()
            curTime = time + bridgeLength
            curWeight -= weight
        }

//        현재 트럭이 다리 위에 올라감
        curWeight += it
        truckQueue.add(Truck(it, curTime))
    }

    println(curTime + bridgeLength)
}