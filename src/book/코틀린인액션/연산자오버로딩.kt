package book.코틀린인액션

data class Point(val x: Int, val y: Int) {
    operator fun plus(other: Point): Point {
        return Point(x + other.x, y + other.y)
    }
}
/**
 * 확장함수로 (-)연산자 정의
 */
operator fun Point.minus(other: Point) : Point {
    return Point(x - other.x, y - other.y)
}

fun main() {
    val p1 = Point(1,2)
    val p2 = Point(3,4)
    val p3 = p1 + p2
    val p4 = p1 - p2
    println(p3)
    println(p4)
}
