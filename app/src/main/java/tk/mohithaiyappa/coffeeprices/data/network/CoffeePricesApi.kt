package tk.mohithaiyappa.coffeeprices.data.network

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import tk.mohithaiyappa.coffeeprices.data.repository.CoffeePricesData
import tk.mohithaiyappa.coffeeprices.data.repository.CoffeePricesDataList


interface CoffeePricesApi{
    @GET("prices/latest")
    suspend fun getLatestPrice():Response<CoffeePricesData>

    @GET("prices")
    fun getAllPrices():Observable<CoffeePricesDataList>
}