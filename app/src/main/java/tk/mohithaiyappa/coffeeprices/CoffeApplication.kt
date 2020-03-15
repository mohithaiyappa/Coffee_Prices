package tk.mohithaiyappa.coffeeprices

import android.app.Application
import tk.mohithaiyappa.coffeeprices.di.CoffeePriceComponent
import tk.mohithaiyappa.coffeeprices.di.DaggerCoffeePriceComponent
import tk.mohithaiyappa.coffeeprices.di.modules.ContextModule

class CoffeeApplication: Application() {

    lateinit var coffeePriceComponent: CoffeePriceComponent

    override fun onCreate() {
        super.onCreate()

        coffeePriceComponent = DaggerCoffeePriceComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}