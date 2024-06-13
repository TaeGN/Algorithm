package 백준.Silver.S4.p24465_데뷔의꿈

import java.time.LocalDate

const val YEAR = 0
const val ALOHA_MEMBER_COUNT = 7

enum class ZodiacSign(val startDate: LocalDate) {
    AQUARIUS(LocalDate.of(YEAR, 1, 20)),
    PISCES(LocalDate.of(YEAR, 2, 19)),
    ARIES(LocalDate.of(YEAR, 3, 21)),
    TAURUS(LocalDate.of(YEAR, 4, 20)),
    GEMINI(LocalDate.of(YEAR, 5, 21)),
    CANCER(LocalDate.of(YEAR, 6, 22)),
    LEO(LocalDate.of(YEAR, 7, 23)),
    VIRGO(LocalDate.of(YEAR, 8, 23)),
    LIBRA(LocalDate.of(YEAR, 9, 23)),
    SCORPIO(LocalDate.of(YEAR, 10, 23)),
    SAGITTARIUS(LocalDate.of(YEAR, 11, 23)),
    CAPRICORN(LocalDate.of(YEAR, 12, 22));
}


fun main() {
    val br = System.`in`.bufferedReader()
    fun String.toLocalDate(): LocalDate = this.split(" ").map(String::toInt).let { LocalDate.of(YEAR, it[0], it[1]) }
    fun LocalDate.findZodiacSign(): ZodiacSign =
        if (dayOfMonth >= ZodiacSign.entries[monthValue - 1].startDate.dayOfMonth) ZodiacSign.entries[monthValue - 1]
        else ZodiacSign.entries[(monthValue - 2 + ZodiacSign.entries.size) % ZodiacSign.entries.size]

    val alohaMemberZodiacSignSet = mutableSetOf<ZodiacSign>()
    repeat(ALOHA_MEMBER_COUNT) {
        val zodiacSign = br.readLine().toLocalDate().findZodiacSign()
        alohaMemberZodiacSignSet.add(zodiacSign)
    }

    val n = br.readLine().toInt()
    val list = mutableListOf<String>()
    val sb = StringBuilder()
    repeat(n) {
        val birth = br.readLine()
        if (birth.toLocalDate().findZodiacSign() !in alohaMemberZodiacSignSet) list.add(birth)
    }
    list.sortBy { dateStr ->
        dateStr.split(" ").map(String::toInt).let { it[0] * 32 + it[1] }
    }
    list.forEach(sb::appendLine)
    println(sb.ifEmpty { "ALL FAILED" })
}