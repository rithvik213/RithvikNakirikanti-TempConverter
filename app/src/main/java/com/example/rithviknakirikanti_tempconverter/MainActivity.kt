package com.example.rithviknakirikanti_tempconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import kotlinx.coroutines.awaitCancellation
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //declare the seekbars and output textView
        val celsiusSeekBar:SeekBar = findViewById(R.id.seekBarCelsius)
        val fahrenheitSeekBar:SeekBar = findViewById(R.id.seekBarFahrenheit)
        val celsiusOutput:TextView = findViewById(R.id.celsiusOutput)
        val fahrenheitOutput:TextView = findViewById(R.id.fahrenehitOutput)

        //set initial values for seekbar and output
        celsiusSeekBar.progress = 0
        celsiusOutput.setText("0")

        fahrenheitSeekBar.progress = 32
        fahrenheitOutput.setText("32")



        celsiusSeekBar.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {

                //set progress of seekbar to the output
                celsiusOutput.setText(celsiusSeekBar.progress.toString())

                //update fahrenheit seekbar
                var fahrenheitConversion: Double = (9.0/5.0 * celsiusSeekBar.progress) + 32
                fahrenheitSeekBar.progress = ceil(fahrenheitConversion).toInt()
                fahrenheitOutput.setText(fahrenheitSeekBar.progress.toString())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }

        })

        fahrenheitSeekBar.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int,
                                           fromUser: Boolean) {

                //set progress of seekbar to the output
                fahrenheitOutput.setText(fahrenheitSeekBar.progress.toString())

                //update celsius seekbar
                var celsiusConversion: Double = (fahrenheitSeekBar.progress - 32) * 5.0/9.0
                celsiusSeekBar.progress = ceil(celsiusConversion).toInt()
                celsiusOutput.setText(celsiusSeekBar.progress.toString())

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                //snap back if fahrenheit < 32
                if(fahrenheitSeekBar.progress < 32) {
                    fahrenheitSeekBar.progress = 32
                }
            }

        })
    }
}