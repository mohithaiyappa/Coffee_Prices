package tk.mohithaiyappa.coffeeprices.repository.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(  entities = arrayOf(
                ScrappedAtEntity::class,
                PricesEntity::class),
            version = 1)
abstract class PricesDatabase : RoomDatabase() {
    abstract fun pricesDao():PricesDao
}