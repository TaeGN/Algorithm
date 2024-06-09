package 백준.Gold.G3.p2623_음악프로그램

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

@JvmInline
value class Singer(val id: Int)

@JvmInline
value class Producer(val id: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()

    val singerSequencesByProducer = List(m) { ArrayDeque<Singer>() }
    val producerListBySinger = List(n + 1) { mutableListOf<Producer>() }
    repeat(m) { producerId ->
        st = StringTokenizer(readLine())
        val singerCount = st.nextToken().toInt()
        val producer = Producer(producerId)
        repeat(singerCount) {
            val singer = Singer(st.nextToken().toInt())
            singerSequencesByProducer[producer.id].add(singer)
            producerListBySinger[singer.id].add(producer)
        }
    }

    val sb = StringBuilder()
    fun Producer.next(): Producer = Producer((id + 1) % m)
    fun query(producer: Producer, nothingCount: Int = 0) {
        if (nothingCount == m) return
        val sequence = singerSequencesByProducer[producer.id]
        if (sequence.isEmpty()) {
            query(producer.next(), nothingCount + 1)
            return
        }

        val singer = sequence.first()
        val producerList = producerListBySinger[singer.id]
        if (producerList.size == 1) {
            sb.appendLine(singer.id)
            sequence.removeFirst()
            query(producer.next())
        } else if (producerList.all { singerSequencesByProducer[it.id].first() == singer }) {
            sb.appendLine(singer.id)
            producerList.forEach { singerSequencesByProducer[it.id].removeFirst() }
            query(producer.next())
        } else {
            for (otherProducer in producerList) {
                if (producer == otherProducer) continue
                query(otherProducer, nothingCount + 1)
            }
        }
    }

    query(Producer(0))
    println(if (singerSequencesByProducer.all { it.size == 0 }) sb else "0")
}

