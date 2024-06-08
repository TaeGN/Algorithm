package 백준.S2.p20006_랭킹전대기열

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.PriorityQueue
import java.util.StringTokenizer

data class Player(val level: Int, val nickname: String) : Comparable<Player> {
    override fun compareTo(other: Player): Int = nickname.compareTo(other.nickname)
    override fun toString(): String = "$level $nickname"
}

data class Room(val createdAt: Int, val manager: Player, val capacity: Int) {
    private val playerQueue = PriorityQueue<Player>(capacity)

    init {
        playerQueue.add(manager)
    }

    fun participate(player: Player): Boolean {
        if (canParticipate(player)) {
            playerQueue.add(player)
            return true
        }

        return false
    }

    private fun canParticipate(player: Player): Boolean = isNotFull() && player.level >= manager.level - 10 && player.level <= manager.level + 10
    private fun isNotFull() = playerQueue.size < capacity
    fun printPlayers() = with(StringBuilder()) {
        if(isNotFull()) append("Waiting!")
        else append("Started!")
        while (playerQueue.isNotEmpty()) append("\n${playerQueue.poll()}")
        println(toString())
    }
}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    var st = StringTokenizer(readLine())
    val totalPlayerCount = st.nextToken().toInt()
    val maxPlayerCountByRoom = st.nextToken().toInt()

    val rooms = mutableListOf<Room>()
    fun findRoom(player: Player): Boolean = rooms.any { it.participate(player) }

    for (time in 0 until totalPlayerCount) {
        st = StringTokenizer(readLine())
        val level = st.nextToken().toInt()
        val nickname = st.nextToken()
        val player = Player(level, nickname)
        if (findRoom(player)) continue
        rooms.add(Room(time, player, maxPlayerCountByRoom))
    }

    rooms.forEach(Room::printPlayers)
}