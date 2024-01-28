package org.example

import java.io.File
import kotlin.random.Random

class WeatherGeneration {
    private var day = 0
    private var month = 0
    private var year = 0
    private var temperatureC = 0

    private fun generateDay() {
        when (month) {
            2 -> day = Random.nextInt(1, 22) // Февраль имеет максимум 28 дней
            4, 6, 9, 11 -> day = Random.nextInt(1, 24) // Апрель, июнь, сентябрь, ноябрь имеют максимум 30 дней
            else -> {
                day = Random.nextInt(1, 25) // Остальные месяцы имеют максимум 31 день
                }
            }
        }


    private fun generateMonth() {
        month = Random.nextInt(1, 12)
    }

    private fun generateYear() {
        year = Random.nextInt(1900, 2025)
    }

    fun generateDate(): String {
        generateDay()
        generateMonth()
        generateYear()
        return "$day.$month.$year"
    }

    fun generateTemperature() {
        when (month) {
            1 -> temperatureC = Random.nextInt(-30, 2)
            2 -> temperatureC = Random.nextInt(-25, 5)
            3 -> temperatureC = Random.nextInt(-15, 10)
            4 -> temperatureC = Random.nextInt(5, 15)
            5 -> temperatureC = Random.nextInt(15, 25)
            6 -> temperatureC = Random.nextInt(18, 30)
            7 -> temperatureC = Random.nextInt(20, 32)
            8 -> temperatureC = Random.nextInt(20, 32)
            9 -> temperatureC = Random.nextInt(10, 22)
            10 -> temperatureC = Random.nextInt(0, 15)
            11 -> temperatureC = Random.nextInt(-10, 10)
            12 -> temperatureC = Random.nextInt(-15, 5)
        }
        println("$temperatureC °C")
    }

    fun weatherForecast() {
        val file = File("weatherForecast.txt")

        file.bufferedWriter().use { out ->
            for (i in 0 until 7) {
                val difference = Random.nextInt(-6, 6)
                val temperatureF = (temperatureC + difference) * 1.8 + 32
                val roundTemp = String.format("%.1f", temperatureF)
                val dayList = (day + i).toString().padStart(2, '0')
                val monthList = month.toString().padStart(2, '0')
                out.write("$dayList.$monthList.$year ${temperatureC + difference}°C $roundTemp℉\n")
            }
        }
    }
}
