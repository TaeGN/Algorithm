package 백준.Gold.G4.p1043_거짓말

import java.io.StreamTokenizer

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun readInt(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = readInt()
    val m = readInt()
    val trueCount = readInt()
    val trueSet = mutableSetOf<Int>()
    repeat(trueCount) {
        trueSet.add(readInt())
    }
    val participateList = List(n + 1) { mutableListOf<Int>() }
    val partyList = List(m) { mutableListOf<Int>() }
    repeat(m) { partyId ->
        val count = readInt()
        repeat(count) {
            val userId = readInt()
            participateList[userId].add(partyId)
            partyList[partyId].add(userId)
        }
    }

    fun checkTrue(
        userId: Int,
        visitedUser: BooleanArray,
        visitedParty: BooleanArray
    ) {
        visitedUser[userId] = true
        for (partyId in participateList[userId]) {
            if (visitedParty[partyId]) continue
            visitedParty[partyId] = true
            for (anotherUserId in partyList[partyId]) {
                if (visitedUser[anotherUserId]) continue
                checkTrue(anotherUserId, visitedUser, visitedParty)
            }
        }
    }

    fun maxFalseCount(): Int {
        val visitedUser = BooleanArray(n + 1)
        val visitedParty = BooleanArray(m)
        for (userId in trueSet) {
            if (visitedUser[userId]) continue
            checkTrue(userId, visitedUser, visitedParty)
        }

        return visitedParty.count { !it }
    }

    println(maxFalseCount())
}