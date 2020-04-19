package tk.mohithaiyappa.coffeeprices.di

import dagger.Component
import tk.mohithaiyappa.coffeeprices.activity.MainActivity
import tk.mohithaiyappa.coffeeprices.di.modules.ApiModule
import tk.mohithaiyappa.coffeeprices.di.modules.ContextModule
import tk.mohithaiyappa.coffeeprices.di.modules.DatabaseModule
import tk.mohithaiyappa.coffeeprices.di.modules.NetworkModule
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope

@ApplicationScope
@Component(modules = [ContextModule::class, ApiModule::class,NetworkModule::class, DatabaseModule::class])
interface CoffeePriceComponent {

    fun inject(mainActivity: MainActivity)
}