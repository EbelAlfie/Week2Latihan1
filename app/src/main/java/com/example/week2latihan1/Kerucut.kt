package com.example.week2latihan1

class Kerucut(override val nama: String, private val r: Int, private val t: Int): Shape() {
    override fun calculateVolume(): Double {
        return (3.14 * r * r * t) / 3
    }
}