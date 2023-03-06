package com.example.week2latihan1

class Bola(override val nama: String, private val r: Int): Shape() {
    override fun calculateVolume(): Double {
        return (4 * 3.14 * r * r * r)/ 3
    }
}