package tk.mohithaiyappa.coffeeprices.repository

import retrofit2.Response
import retrofit2.http.GET


interface CoffeePricesApi{
    @GET("prices/latest")
    suspend fun getLatestPrice():Response<CoffeePricesData>

    @GET("prices")
    suspend fun getAllPrices():Response<CoffeePricesDataList>
}