package tk.mohithaiyappa.coffeeprices.data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.customviews.BarGraph
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
        private var barGraph: BarGraph = view.findViewById(R.id.barGraph)

        fun setup(position: Int) {
            tv_spice_name.text = getItem(position).spiceName
            tv_spice_price.text = getItem(position).spiceCost
            rv_image_view.setImageResource(getRightResource(getItem(position).status))
            barGraph.submitData(getItem(position).graphPricesList)

        }

        private fun getRightResource(value: Int): Int {
            return when (value) {
                1 -> R.drawable.ic_polygon_up
                -1 -> R.drawable.ic_polygon_down
                else -> R.drawable.ic_unchanged
            }
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