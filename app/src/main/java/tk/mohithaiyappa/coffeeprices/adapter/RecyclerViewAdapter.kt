package tk.mohithaiyappa.coffeeprices.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.repository.CoffeePricesDataList

class RecyclerViewAdapter(
    private val newData: CoffeePricesDataList.Data,
    private val oldData: CoffeePricesDataList.Data,
    val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var tv_spice_name: TextView = view.findViewById(R.id.tv_spice_name) as TextView
        private var tv_spice_price: TextView = view.findViewById(R.id.tv_price) as TextView
        private var rv_image_view: ImageView = view.findViewById(R.id.rv_image_view) as ImageView

        fun setup(position: Int) {
            tv_spice_name.text = newData.prices[position].spiceName
            tv_spice_price.text = newData.prices[position].spiceCost
            rv_image_view.setImageResource(getRightResource(position))

        }

        private fun getRightResource(position: Int): Int {
            val newPrice: Int = convert(newData.prices[position].spiceCost.trim())
            val oldPrice: Int = convert(oldData.prices[position].spiceCost.trim())
            return when {
                newPrice == oldPrice -> {
                    R.drawable.ic_unchanged
                }
                newPrice > oldPrice -> {
                    R.drawable.ic_polygon_up
                }
                else -> {
                    R.drawable.ic_polygon_down
                }
            }
        }

        private fun convert(string: String): Int {
            var newString = string.substringAfter(' ')
            newString = newString.substringBefore(' ')
            return newString.toInt()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_coffee_prices_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = newData.prices.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(position)
    }
}