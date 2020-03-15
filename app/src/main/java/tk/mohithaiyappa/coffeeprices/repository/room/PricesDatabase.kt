package tk.mohithaiyappa.coffeeprices.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE= "price"

@Database(entities = [ScrappedAtEntity::class, PricesEntity::class], version = 1)
abstract class PricesDatabase : RoomDatabase() {
    abstract fun pricesDao():PricesDao
}