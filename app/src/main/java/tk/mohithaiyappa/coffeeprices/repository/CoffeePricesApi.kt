package tk.mohithaiyappa.coffeeprices.repository

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET


interface CoffeePricesApi{
    @GET("prices/latest")
    suspend fun getLatestPrice():Response<CoffeePricesData>

    @GET("prices")
    fun getAllPrices():Observable<CoffeePricesDataList>
}