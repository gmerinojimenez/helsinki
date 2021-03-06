package com.example.myapplication

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.TransitionManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.BounceInterpolator
import android.widget.ImageButton
import android.widget.TextView
import com.db.chart.animation.Animation
import com.db.chart.model.Bar
import com.db.chart.model.BarSet
import com.db.chart.model.LineSet
import com.db.chart.view.BarChartView
import com.db.chart.view.LineChartView
import com.example.myapplication.adapter.CallUsageAdapter
import com.example.myapplication.adapter.DataUsageAdapter


class ScrollingActivity : AppCompatActivity() {

    lateinit var recylclerView: RecyclerView

    lateinit var button1: ImageButton
    lateinit var button2: ImageButton
    lateinit var button3: ImageButton
    lateinit var button4: ImageButton

    var currentPosition = 0

    lateinit var barChart: BarChartView
    lateinit var lineChart: LineChartView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        window.statusBarColor = resources.getColor(R.color.black_opaque, null)


        setContentView(R.layout.activity_scrolling)
        changeColor(0)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button1.setOnClickListener { onClick(0) }
        button2.setOnClickListener { onClick(1) }
        button3.setOnClickListener { onClick(2) }
        button4.setOnClickListener { onClick(3) }

        loadChart()
        loadUsageList(currentPosition)

    }

    private fun loadChart() {
        when (currentPosition) {
            0 -> drawLineChart()
            1 -> drawBarChart()
            2 -> drawBarChart()
            else -> drawLineChart()
        }
    }

    private fun loadUsageList(position: Int) {
        recylclerView = findViewById(R.id.recyclerview)
        when (position) {
            0 -> loadDataUsageList()
            1 -> loadCallUsageList()
            2 -> loadSmsList()
            else -> loadOtherCostList()
        }
    }

    private fun loadOtherCostList() {
        loadDataUsageList()
    }

    private fun loadSmsList() {
        loadCallUsageList()
    }

    private fun loadCallUsageList() {
        val callAdapter = CallUsageAdapter(provideCallUsage(), this)
        recylclerView.adapter = callAdapter
        recylclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadDataUsageList() {
        val dataUsageAdapter = DataUsageAdapter(provideDataUsage())
        recylclerView.adapter = dataUsageAdapter
        recylclerView.layoutManager = LinearLayoutManager(this)
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
        loadUsageList(position)
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
        when (position) {
            0 -> updateLineChart(position)
            1 -> drawBarChart()
            2 -> drawBarChart()
            else -> updateLineChart(position)
        }
    }

    private fun updateLineChart(position: Int) {
        if (lineChart == null) {
            drawLineChart()
            return
        }
        findViewById<View>(R.id.barchart).visibility = View.GONE
        lineChart = findViewById<LineChartView>(R.id.linechart)
        lineChart.visibility = View.VISIBLE
        val newValues = when (position) {
            0 -> values1
            1 -> values2
            2 -> values3
            3 -> values4
            else -> values1
        }
        lineChart.updateValues(0, newValues.toFloatArray())
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

        lineChart.notifyDataUpdate()
    }


    private fun drawLineChart() {
        findViewById<TextView>(R.id.usageText).text = "18GB"
        findViewById<TextView>(R.id.costUsage).text = "6,22€"

        lineChart = findViewById<LineChartView>(R.id.linechart)
        lineChart.visibility = View.VISIBLE
        findViewById<View>(R.id.barchart).visibility = View.GONE
        lineChart.setYAxis(false)
        lineChart.setLabelsColor(ContextCompat.getColor(this, R.color.white))
        lineChart.setAxisColor(ContextCompat.getColor(this, R.color.grey3))
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

        lineChart.addData(dataset)
        lineChart!!.setClickablePointRadius(5F)

        val animation = com.db.chart.animation.Animation(1000)

        animation.setInterpolator(BounceInterpolator())
                .fromAlpha(0)

        lineChart.show(animation)
//        chart.background = R.color.black_opaque
    }

    private fun drawBarChart() {
        findViewById<TextView>(R.id.usageText).text = "1h 20m 32s"
        findViewById<TextView>(R.id.costUsage).text = "4,30€"

        barChart = findViewById(R.id.barchart)
        barChart.visibility = View.VISIBLE
        findViewById<View>(R.id.linechart).visibility = View.GONE
//        Label
        barChart.setLabelsColor(ContextCompat.getColor(this, R.color.white))
//      Axis
        barChart.setXAxis(false)
        barChart.setYAxis(false)
        barChart.setAxisBorderValues(0f, 30f, 1f)

        barChart.setRoundCorners(5f)

        val barSet = BarSet()
        for (i in 0..22) {
            val bar = Bar("", randInt(5, 20).toFloat())
            bar.color = ContextCompat.getColor(this, R.color.movistarGreen)
            barSet.addBar(bar)
        }
        for (i in 0..8) {
            val bar = Bar("", 0f)
            bar.color = ContextCompat.getColor(this, R.color.movistarGreen)
            barSet.addBar(bar)
        }
        barChart.addData(barSet)

        barChart.show(getAnimation(30))
    }

    private fun getAnimation(numberOfBars: Int): Animation {
        val beginOrder = IntArray(numberOfBars)
        for (i in 0 until numberOfBars) {
            beginOrder[i] = i
        }
        return Animation()
    }
}
