package tk.mohithaiyappa.coffeeprices.repository.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface PricesDao{
    @Query("select * from PricesEntity where scrappedId = :id")
    suspend fun getLatestPrices(id:String):List<PricesEntity>

    @Insert
    suspend fun setLatestPrices(prices:PricesEntity)

    @Insert
    suspend fun setScrapingInfo(info:ScrappedAtEntity)
}