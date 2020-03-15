package tk.mohithaiyappa.coffeeprices.di.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import tk.mohithaiyappa.coffeeprices.repository.room.DATABASE
import tk.mohithaiyappa.coffeeprices.repository.room.PricesDao
import tk.mohithaiyappa.coffeeprices.repository.room.PricesDatabase

@Module(includes = [ContextModule::class])
class DatabaseModule {

    @Provides
    @ApplicationScope
    fun provideDatabase(context: Application): PricesDatabase {
        return Room.databaseBuilder(context,PricesDatabase::class.java, DATABASE).build()
    }

    @Provides
    @ApplicationScope
    fun providePricesDao(database: PricesDatabase) : PricesDao {
        return database.pricesDao()
    }
}