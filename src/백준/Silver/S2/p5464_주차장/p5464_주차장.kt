package 백준.Silver.S2.p5464_주차장

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.abs

data class ParkingSpace(val hourlyParkingFee: Int) {
    private var car: Car? = null
    fun isEmpty() = car == null
    fun parking(car: Car) {
        this.car = car
    }

    fun out(): Int {
        val fee = car?.let { it.weight * hourlyParkingFee } ?: throw Exception("주차된 차량 없음")
        car = null
        return fee
    }

    fun isMatch(car: Car) = this.car?.carId == car.carId
}

data class Car(val carId: Int, val weight: Int)

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    val st = StringTokenizer(readLine())
    val n = st.nextToken().toInt()
    val m = st.nextToken().toInt()
    val parkingSpaces = (1..n).map { ParkingSpace(readLine().toInt()) }
    val cars = (1..m).map { Car(it, readLine().toInt()) }
    val queue = ArrayDeque<Car>()

    var totalPrice = 0
    for (i in 1..(2 * m)) {
        val input = readLine().toInt()
        val car = cars[abs(input) - 1]
        val isIn = input > 0
        if (isIn) {
            parkingSpaces.find(ParkingSpace::isEmpty)?.parking(car) ?: queue.add(car)
        } else {
            val parkingSpace = parkingSpaces.find { it.isMatch(car) } ?: throw Exception()
            totalPrice += parkingSpace.out()
            if (queue.isNotEmpty()) parkingSpace.parking(queue.removeFirst())
        }
    }
    close()
    println(totalPrice)
}