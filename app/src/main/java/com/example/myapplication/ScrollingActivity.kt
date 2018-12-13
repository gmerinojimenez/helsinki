package com.example.myapplication

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import com.db.chart.model.LineSet
import com.db.chart.view.LineChartView
import android.graphics.Color.parseColor
import android.view.animation.BounceInterpolator


class ScrollingActivity : AppCompatActivity() {

    lateinit var button1: ImageButton
    lateinit var button2: ImageButton
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton

    var currentPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = resources.getColor(R.color.black_opaque, null)


        setContentView(R.layout.activity_scrolling)
        drawBarChart()

        changeColor(0)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener { onClick(0) }
        button2.setOnClickListener { onClick(1) }
        button3.setOnClickListener { onClick(2) }
        button4.setOnClickListener { onClick(3) }

    }

    var currentColor = 0

    fun changeColor(position: Int) {
        val colorFrom = resources.getColor(R.color.blue)
        val colorTo = resources.getColor(when (position) {
            0 -> R.color.blue
            1 -> R.color.movistarGreen
            2 -> R.color.orange
            3 -> R.color.purple
            else -> R.color.blue
        })
        currentColor = colorTo
        val colorAnimation = ValueAnimator.ofObject(ArgbEvaluator(), colorFrom, colorTo)
        colorAnimation.duration = 250 // milliseconds
        colorAnimation.addUpdateListener { animator -> findViewById<View>(R.id.header).setBackgroundColor(animator.animatedValue as Int) }
        colorAnimation.start()

    }

    private fun transition(position: Int) {

        TransitionManager.beginDelayedTransition(when (position) {
            0 -> findViewById(R.id.transitions_container1)
            1 -> findViewById(R.id.transitions_container2)
            2 -> findViewById(R.id.transitions_container3)
            3 -> findViewById(R.id.transitions_container4)
            else -> findViewById(R.id.transitions_container1)
        })

        when (currentPosition) {
            0 -> findViewById<View>(R.id.text1)
            1 -> findViewById<View>(R.id.text2)
            2 -> findViewById<View>(R.id.text3)
            3 -> findViewById<View>(R.id.text4)
            else -> findViewById<View>(R.id.text1)
        }.visibility = View.GONE

        when (position) {
            0 -> findViewById<View>(R.id.text1)
            1 -> findViewById<View>(R.id.text2)
            2 -> findViewById<View>(R.id.text3)
            3 -> findViewById<View>(R.id.text4)
            else -> findViewById<View>(R.id.text1)
        }.visibility = View.VISIBLE

//        when(position) {
//            0 -> findViewById<View>(R.id.button1)
//            1 -> findViewById<View>(R.id.button2)
//            2 -> findViewById<View>(R.id.button3)
//            3 -> findViewById<View>(R.id.button4)
//            else -> findViewById<View>(R.id.button1)
//        }.setBackgroundResource(R.drawable.icn_1_pink)


//        val intArray = IntArray(2)
//        button2.getLocationOnScreen(intArray)
//
//
//        var x: Int = intArray[0]
//        var y: Int = intArray[1]
//
//
//        val anim = SpringAnimation(button1, DynamicAnimation.TRANSLATION_X, x.toFloat())
//        anim.getSpring().setDampingRatio(10F)
//        anim.getSpring().setStiffness(10F)
//        anim.start()
    }

    fun onClick(position: Int) {
//        val transition = findViewById<View>(R.id.header).background as ColorDrawable
//        transition.
        transition(position)
        changeColor(position)
//        changeTitle(position)

        updateChart(position)
        currentPosition = position
    }

    private fun changeTitle(position: Int) {
        TransitionManager.beginDelayedTransition(findViewById(R.id.header),
                ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN))
        findViewById<TextView>(R.id.title).setText("test")

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

    val values1 = listOf(3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 3.0f
            , 4.5f, 2.5f, 2.5f)

    val values2 = listOf(3.5f, 4.7f, 4.3f, 3f, 6f, 1f, 7f, 8.3f, 7.0f
            , 4.5f, 2.5f, 2.5f)


    val values3 = listOf(3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 3f, 8.3f, 7.0f
            , 4.5f, 7f, 2.5f)

    val values4 = listOf(3.5f, 9f, 1f, 8f, 6.5f, 9.9f, 6f, 8.3f, 7.0f
            , 4.5f, 2.5f, 4f)


    val months = listOf("Enero",
            "Febrero",
            "Marzo",
            "Abril",
            "Mayo",
            "Junio",
            "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")

    private fun updateChart(position: Int) {
        val chart = findViewById<LineChartView>(R.id.barchart)

        val newValues = when (position) {
            0 -> values1
            1 -> values2
            2 -> values3
            3 -> values4
            else -> values1
        }
        chart.updateValues(0, newValues.toFloatArray())
//        chart.updateValues(1, newValues.toFloatArray())
//        chart.updateValues(2, newValues.toFloatArray())
//        chart.updateValues(3, newValues.toFloatArray())
//        chart.updateValues(4, newValues.toFloatArray())
//        chart.updateValues(5, newValues.toFloatArray())
//        chart.updateValues(7, newValues.toFloatArray())
//        chart.updateValues(8, newValues.toFloatArray())
//        chart.updateValues(9, newValues.toFloatArray())
//        chart.updateValues(10, newValues.toFloatArray())
//        chart.updateValues(11, newValues.toFloatArray())

        chart.notifyDataUpdate()
    }


    private fun drawBarChart() {
        val chart = findViewById<LineChartView>(R.id.barchart)

        val values1 = listOf(3.5f, 4.7f, 4.3f, 8f, 6.5f, 9.9f, 7f, 8.3f, 7.0f
                , 4.5f, 2.5f, 2.5f)


        val dataset = LineSet(months.toTypedArray(), values1.toFloatArray())

        dataset.setColor(Color.parseColor("#00A9E0"))
//                .setFill(Color.parseColor("#2d374c"))
                .setDotsColor(Color.parseColor("#758cbb"))
                .setThickness(4f)
                .setSmooth(true)
                .setThickness(10F)
                .beginAt(0)

        chart.addData(dataset)
        chart!!.setClickablePointRadius(5F)
        chart.setYAxis(false)

        val animation = com.db.chart.animation.Animation(1000)

        animation.setInterpolator(BounceInterpolator())
                .fromAlpha(0)

//        new Animation().setInterpolator(new BounceInterpolator())
//                .fromAlpha(0)
//                .withEndAction(chartAction))

        chart.show(animation)
//        chart.background = R.color.black_opaque
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
