package tk.mohithaiyappa.coffeeprices.data.adapter

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.data.model.LatestSpiceData


class RecyclerViewAdapter :
    ListAdapter<LatestSpiceData.Data, RecyclerViewAdapter.ViewHolder>(diff) {

    companion object {
        val diff = object : DiffUtil.ItemCallback<LatestSpiceData.Data>() {
            override fun areItemsTheSame(
                oldItem: LatestSpiceData.Data,
                newItem: LatestSpiceData.Data
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: LatestSpiceData.Data,
                newItem: LatestSpiceData.Data
            ): Boolean {
                return oldItem == newItem
            }

        }
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tv_spice_name: TextView = view.findViewById(R.id.tv_spice_name) as TextView
        private var tv_spice_price: TextView = view.findViewById(R.id.tv_price) as TextView
        private var rv_image_view: ImageView = view.findViewById(R.id.rv_image_view) as ImageView
        private var barChart: BarChart = view.findViewById(R.id.barChart) as BarChart

        fun setup(position: Int) {
            tv_spice_name.text = getItem(position).spiceName
            tv_spice_price.text = getItem(position).spiceCost
            rv_image_view.setImageResource(getRightResource(getItem(position).status))
            setupBarChart(position)

        }

        private fun setupBarChart(position: Int) {
            barChart.data = getBarData(position)
            barChart.axisRight.setDrawGridLines(false)
            barChart.axisLeft.setDrawGridLines(false)
            barChart.xAxis.setDrawGridLines(false)
            barChart.setDrawBorders(false)
            barChart.setBorderColor(Color.TRANSPARENT)
            barChart.background = ColorDrawable(Color.TRANSPARENT)
            barChart.description.isEnabled = false
            barChart.axisLeft.setDrawLabels(false)
            barChart.axisRight.setDrawLabels(false)
            //barChart.xAxis.setDrawLabels(false);

            barChart.legend.isEnabled = false
            val xAxis: XAxis = barChart.xAxis
            val xaxis = barChart.xAxis
            xaxis.labelRotationAngle = -90f
            xAxis.isEnabled = false
            barChart.isSelected = false
            barChart.setOnClickListener { }
            barChart.setTouchEnabled(false)
            val yAxis: YAxis = barChart.axisLeft
            yAxis.setDrawTopYLabelEntry(false)
            yAxis.isInverted = false



            barChart.animateY(1000, Easing.EaseInBounce)
            barChart.invalidate()
        }

        private fun getRightResource(value: Int): Int {
            return when (value) {
                1 -> R.drawable.ic_polygon_up
                -1 -> R.drawable.ic_polygon_down
                else -> R.drawable.ic_unchanged
            }
        }

        private fun getBarData(position: Int): BarData {
            var barDataSet = BarDataSet(dataValues(), "Last seven Days Data")
            barDataSet.color = Color.parseColor("#442c2e")
            var barData = BarData()
            barData.addDataSet(barDataSet)

            return barData
        }

        private fun dataValues(): List<BarEntry> {
            var barEntryArray: ArrayList<BarEntry> = ArrayList<BarEntry>()
            barEntryArray.add(BarEntry(0f, 3500f, "Mon"))
            barEntryArray.add(BarEntry(1f, 3400f, "Tue"))
            barEntryArray.add(BarEntry(2f, 3350f, "Wed"))
            barEntryArray.add(BarEntry(3f, 3450f, "Thu"))
            barEntryArray.add(BarEntry(4f, 3600f, "Fri"))
            barEntryArray.add(BarEntry(5f, 3300f, "Sat"))
            barEntryArray.add(BarEntry(6f, 3400f, "Sun"))

            return barEntryArray
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_coffee_prices_view, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(position)
    }
}