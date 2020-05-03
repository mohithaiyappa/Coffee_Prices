package tk.mohithaiyappa.coffeeprices.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import tk.mohithaiyappa.coffeeprices.data.model.LatestSpiceData


interface CoffeePricesApi{
    @GET("prices/latest")
    fun getLatestPrice():Observable<LatestSpiceData>
}