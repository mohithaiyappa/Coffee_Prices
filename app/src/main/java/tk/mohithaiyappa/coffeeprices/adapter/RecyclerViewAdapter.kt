package tk.mohithaiyappa.coffeeprices.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.repository.CoffeePricesData
import tk.mohithaiyappa.coffeeprices.repository.CoffeePricesDataList

class RecyclerViewAdapter(private val  newData : CoffeePricesDataList.Data,
                          private val oldData : CoffeePricesDataList.Data,
                          val context:Context): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    init {
        Toast.makeText(context,"starting adapter",Toast.LENGTH_SHORT).show()
    }


    inner class ViewHolder(view:View): RecyclerView.ViewHolder(view) {
        private  var tv_spice_name: TextView
        private  var tv_spice_price: TextView
        init {
            tv_spice_name = view.findViewById(R.id.tv_spice_name)as TextView
            tv_spice_price = view.findViewById(R.id.tv_price)as TextView
        }
        fun setup(position: Int){
            tv_spice_name.text=newData.prices[position].spiceName
            tv_spice_price.text=newData.prices[position].spiceCost
            val newPrice:Int =convert(newData.prices[position].spiceCost.trim())
            val oldPrice:Int =convert(oldData.prices[position].spiceCost.trim())
            if (newPrice>=oldPrice)tv_spice_price.setTextColor(Color.GREEN)
            else tv_spice_price.setTextColor(Color.RED)
        }
        fun convert(string:String):Int{
            var newString = string.substringAfter(' ')
            newString=newString.substringBefore(' ')
            return newString.toInt()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_coffee_prices_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        //return priceList.size
        return newData.prices.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setup(position)

    }
}