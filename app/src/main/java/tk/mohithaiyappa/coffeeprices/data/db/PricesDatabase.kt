package tk.mohithaiyappa.coffeeprices.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import tk.mohithaiyappa.coffeeprices.data.db.entities.PricesEntity
import tk.mohithaiyappa.coffeeprices.data.db.entities.ScrappedAtEntity

const val DATABASE= "price"

@Database(entities = [ScrappedAtEntity::class, PricesEntity::class], version = 1)
abstract class PricesDatabase : RoomDatabase() {
    abstract fun pricesDao(): PricesDao
}