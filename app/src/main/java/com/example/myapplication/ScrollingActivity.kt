package com.example.myapplication

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.db.chart.animation.Animation
import com.db.chart.model.Bar
import com.db.chart.model.BarSet
import com.db.chart.view.BarChartView
import java.util.*


class ScrollingActivity : AppCompatActivity() {

    lateinit var button1: ImageButton
    lateinit var button2: ImageButton
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = resources.getColor(R.color.black_opaque, null)


        setContentView(R.layout.activity_scrolling)
        drawBarChart()

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener { onClick(0) }
        button1.setOnClickListener { onClick(1) }
        button1.setOnClickListener { onClick(2) }
        button1.setOnClickListener { onClick(3) }
    }

    fun onClick(position: Int) {
//        val transition = findViewById<View>(R.id.header).background as ColorDrawable
//        transition.
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    private fun drawBarChart() {
        val chart = findViewById<BarChartView>(R.id.barchart)

//        Label
        chart.setLabelsColor(ContextCompat.getColor(this, R.color.white))
//      Axis
        chart.setXAxis(false)
        chart.setYAxis(false)
        chart.setAxisBorderValues(0f, 30f, 1f)

        chart.setRoundCorners(5f)

        val barSet = BarSet()
        for (i in 0..22) {
            val bar = Bar("", randInt(5, 20).toFloat())
            bar.color = ContextCompat.getColor(this, R.color.blue)
            barSet.addBar(bar)
        }
        for (i in 0..8) {
            val bar = Bar("", 0f)
            bar.color = ContextCompat.getColor(this, R.color.blue)
            barSet.addBar(bar)
        }
        chart.addData(barSet)

        chart.show(getAnimation(30))
    }

    private fun getAnimation(numberOfBars: Int): Animation {
        val beginOrder = IntArray(numberOfBars)
        for (i in 0 until numberOfBars) {
            beginOrder[i] = i
        }
        return Animation()
    }

    private fun randInt(min: Int, max: Int): Int {
        return Random().nextInt(max - min + 1) + min
    }

}
