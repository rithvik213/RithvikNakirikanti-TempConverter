package com.example.rithviknakirikanti_tempconverter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Declare the seekbars and output textView
        val celsiusSeekBar: SeekBar = findViewById(R.id.seekBarCelsius)
        val fahrenheitSeekBar: SeekBar = findViewById(R.id.seekBarFahrenheit)
        val celsiusOutput: TextView = findViewById(R.id.celsiusOutput)
        val fahrenheitOutput: TextView = findViewById(R.id.fahrenehitOutput)
        val interestingMessage: TextView = findViewById(R.id.interestingMessage)

        // Set initial values for seekbar and output
        celsiusSeekBar.progress = 0
        celsiusOutput.text = String.format("%d", 0)

        fahrenheitSeekBar.progress = 32
        fahrenheitOutput.text = String.format("%d", 32)

        //set initial text message
        interestingMessage.setText(R.string.warmer_message)

        celsiusSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // Update Celsius output
                    celsiusOutput.text = progress.toString()

                    //update interesting message
                    if(progress <= 20) {
                        interestingMessage.setText(R.string.warmer_message)
                    }
                    else {
                        interestingMessage.setText(R.string.colder_message)

                    }

                    // Calculate and update Fahrenheit seekbar and output
                    val fahrenheitConversion: Int = Math.round((9.0 / 5.0 * progress) + 32).toInt()
                    if (fahrenheitSeekBar.progress != fahrenheitConversion) {
                        fahrenheitSeekBar.progress = fahrenheitConversion
                        fahrenheitOutput.text = fahrenheitConversion.toString()
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

        fahrenheitSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // Update Fahrenheit output
                    fahrenheitOutput.text = progress.toString()

                    //only run this code if progress > 32
                    if(progress > 32) {
                        // Calculate and update Celsius seekbar and output
                        val celsiusConversion: Int = Math.round((progress - 32) * 5.0 / 9.0).toInt()
                        if (celsiusSeekBar.progress != celsiusConversion) {
                            celsiusSeekBar.progress = celsiusConversion
                            celsiusOutput.text = celsiusConversion.toString()
                        }

                        //update interesting message
                        if(celsiusSeekBar.progress <= 20) {
                            interestingMessage.setText(R.string.warmer_message)
                        }
                        else {
                            interestingMessage.setText(R.string.colder_message)

                        }
                    }
                    else {
                        celsiusSeekBar.progress = 0
                        celsiusOutput.setText("0")
                    }
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // Snap back if Fahrenheit < 32
                if (fahrenheitSeekBar.progress < 32) {
                    fahrenheitSeekBar.progress = 32
                    fahrenheitOutput.text = "32"
                }
            }
        })
    }
}
