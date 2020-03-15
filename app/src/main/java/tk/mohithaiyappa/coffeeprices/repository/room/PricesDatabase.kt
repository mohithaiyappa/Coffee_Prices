package tk.mohithaiyappa.coffeeprices.repository.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

private const val DATABASE= "price"
@Database(  entities = arrayOf(
                ScrappedAtEntity::class,
                PricesEntity::class),
            version = 1)
abstract class PricesDatabase : RoomDatabase() {
    abstract fun pricesDao():PricesDao

    companion object{
        @Volatile
        private var instance :PricesDatabase? = null

        fun getInstance(context:Context):PricesDatabase{
            return instance?: synchronized(this){
                instance
                    ?: buildDatabase(context).also { instance=it }
            }
        }

        private fun buildDatabase(context:Context):PricesDatabase{
            return Room.databaseBuilder(context,PricesDatabase::class.java, DATABASE).build()
        }
    }
}