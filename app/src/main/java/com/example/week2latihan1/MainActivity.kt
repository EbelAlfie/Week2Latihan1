package com.example.week2latihan1

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var spinner: Spinner
    private lateinit var etInput1: EditText
    private lateinit var etInput2: EditText
    private lateinit var etInput3: EditText
    private lateinit var tvHasil: TextView
    private lateinit var btnHitung: Button

    private var choice = "Kubus"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        initAdapter()

        spinner.onItemSelectedListener = this

        btnHitung.setOnClickListener {
            calculateVolume()
        }
    }

    private fun initView() {
        spinner = findViewById(R.id.sp_option)
        etInput1 = findViewById(R.id.et_input1)
        etInput2 = findViewById(R.id.et_input2)
        etInput3 = findViewById(R.id.et_input3)
        tvHasil = findViewById(R.id.tv_hasil)
        btnHitung = findViewById(R.id.btn_hitung)
    }

    private fun initAdapter() {
        ArrayAdapter.createFromResource(
            this,
            R.array.options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    @SuppressLint("SetTextI18n")
    private fun calculateVolume() {
        try {
            tvHasil.text = when (choice) {//error input blank
                getString(R.string.kubus), getString(R.string.balok), getString(R.string.tabung) -> {
                    val s1 = Integer.valueOf(etInput1.text.toString())
                    val s2 = if (choice != getString(R.string.kubus)) Integer.valueOf(etInput2.text.toString()) else s1
                    val s3 = if (choice == getString(R.string.tabung)) 1 else if (choice == getString(R.string.kubus)) s1 else Integer.valueOf(etInput3.text.toString())
                    Prism(choice, s1, s2, s3).calculateVolume().toString()
                }
                getString(R.string.kerucut) -> {
                    Kerucut(choice, Integer.valueOf(etInput1.text.toString()), Integer.valueOf(etInput2.text.toString())).calculateVolume().toString()
                }
                getString(R.string.bola) -> {
                    Bola(choice, Integer.valueOf(etInput1.text.toString())).calculateVolume().toString()
                }
                else -> {""}
            } + getString(R.string.satuan)
        } catch(e: Exception) {
            Toast.makeText(this, getString(R.string.error_msg_falseinput), Toast.LENGTH_LONG).show()
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        choice = p0?.getItemAtPosition(p2).toString()
        when (p2) {
            1 -> {
                etInput3.visibility = View.VISIBLE
                etInput2.visibility = View.VISIBLE
            }
            2, 3 -> {//Kerucut
                etInput3.visibility = View.GONE
                etInput2.visibility = View.VISIBLE
            }
            0, 4 -> {//Bola
                etInput3.visibility = View.GONE
                etInput2.visibility = View.GONE
            }
        }
        printHint(choice)
    }

    private fun printHint(choice: String) {
        when(choice) {
            getString(R.string.kubus) -> {
                etInput1.hint = getString(R.string.masukan_sisi_kubus)
            }
            getString(R.string.balok) -> {
                etInput1.hint = getString(R.string.panjang_balok)
                etInput2.hint = getString(R.string.lebar_balok)
                etInput3.hint = getString(R.string.tinggi_balok)
            }
            getString(R.string.tabung) -> {
                etInput1.hint = getString(R.string.jari_jari_tabung)
                etInput2.hint = getString(R.string.tinggi_tabung)
            }
            getString(R.string.bola) -> {
                etInput1.hint = getString(R.string.jari_jari_bola)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?){}
}