package tk.mohithaiyappa.coffeeprices.data.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.data.model.LatestSpiceData

class RecyclerViewAdapter(
    val dataList: List<LatestSpiceData.Data>,
    val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {



    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tv_spice_name: TextView = view.findViewById(R.id.tv_spice_name) as TextView
        private var tv_spice_price: TextView = view.findViewById(R.id.tv_price) as TextView
        private var rv_image_view: ImageView = view.findViewById(R.id.rv_image_view) as ImageView

        fun setup(position: Int) {
            tv_spice_name.text = dataList[position].spiceName
            tv_spice_price.text = dataList[position].spiceCost
            rv_image_view.setImageResource(getRightResource(dataList[position].status))

        }

        private fun getRightResource(value: Int): Int {
            return when (value) {
                 1 ->   R.drawable.ic_polygon_up
                -1 ->   R.drawable.ic_polygon_down
                else -> R.drawable.ic_unchanged
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_coffee_prices_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount()=dataList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(position)
    }
}