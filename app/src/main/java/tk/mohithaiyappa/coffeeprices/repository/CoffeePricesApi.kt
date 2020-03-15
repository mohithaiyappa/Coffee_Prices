package tk.mohithaiyappa.coffeeprices.repository

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface CoffeePricesApi{
    @GET("prices/latest")
    suspend fun getLatestPrice():Response<CoffeePricesData>
    @GET("prices")
    suspend fun getAllPrices():Response<CoffeePricesDataList>

    companion object{
         fun startApi():CoffeePricesApi{
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://coffeemarketprice.herokuapp.com/")
                .build()
                .create(CoffeePricesApi::class.java)
        }
    }
}