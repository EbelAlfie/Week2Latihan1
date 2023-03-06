package com.example.week2latihan1

class Prism(override val nama: String, private val s1: Int, private val s2: Int, private val s3: Int): Shape() {
    override fun calculateVolume(): Double {
        return if (nama == "Tabung") (s1 * s1 * 3.14 * s2) else (s1 * s2 * s3).toDouble()
    }
}