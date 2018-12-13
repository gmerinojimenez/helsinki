package com.example.myapplication

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import com.db.chart.view.BarChartView


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

        button1.setOnClickListener { onClick(0)}
        button1.setOnClickListener { onClick(1)}
        button1.setOnClickListener { onClick(2)}
        button1.setOnClickListener { onClick(3)}
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
    }

//    protected fun generateStackBarValues(days: Int): Array<FloatArray> {
//
//        val stackValues = Array(MAX_NUMBER_OF_STACKS) { FloatArray(days) }
//
//        val start = Calendar.getInstance()
//        start.timeInMillis = selectedStartDate
//        start.set(Calendar.HOUR_OF_DAY, 0)
//        start.set(Calendar.MINUTE, 0)
//        start.set(Calendar.SECOND, 0)
//
//        val end = start.clone() as Calendar
//        end.add(Calendar.DAY_OF_YEAR, 1)
//
//        stackMaxBarValue = 1
//        totalIncomingValue = 0
//        totalOutgoingValue = 0
//
//        var maxAccumulatedDay: Int
//
//
//        //For each day in the date interval
//        for (i in 0 until days) {
//            maxAccumulatedDay = 0
//            var acummulatedPerDay: Int
//            if (showOutgoing) {
//                acummulatedPerDay = calculateAccumulatedInTime(start.timeInMillis, end.timeInMillis, OUTGOING)
//                stackValues[OUTGOING][i] = Math.round(acummulatedPerDay / 60.0).toInt()
//                maxAccumulatedDay += stackValues[OUTGOING][i]
//                totalOutgoingValue += acummulatedPerDay
//            }
//
//            if (showIncoming) {
//                acummulatedPerDay = calculateAccumulatedInTime(start.timeInMillis, end.timeInMillis, INCOMING)
//                stackValues[INCOMING][i] = Math.round(acummulatedPerDay / 60.0).toInt()
//                maxAccumulatedDay += stackValues[INCOMING][i]
//                totalIncomingValue += acummulatedPerDay
//
//            }
//            //Calculate max accumulated
//            if (stackMaxBarValue < maxAccumulatedDay) {
//                stackMaxBarValue = maxAccumulatedDay
//            }
//
//            //Increment one day
//            start.add(Calendar.DAY_OF_YEAR, 1)
//            end.add(Calendar.DAY_OF_YEAR, 1)
//        }
//
//        totalOutgoingValue = Math.round(totalOutgoingValue / 60)
//        totalIncomingValue = Math.round(totalIncomingValue / 60)
//
//        return stackValues
//    }
}
