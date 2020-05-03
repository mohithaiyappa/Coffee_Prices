package tk.mohithaiyappa.coffeeprices.di

import dagger.Component
import tk.mohithaiyappa.coffeeprices.di.modules.ApiModule
import tk.mohithaiyappa.coffeeprices.di.modules.ContextModule
import tk.mohithaiyappa.coffeeprices.di.modules.NetworkModule
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import tk.mohithaiyappa.coffeeprices.ui.MainActivity

@ApplicationScope
@Component(modules = [ContextModule::class, ApiModule::class,NetworkModule::class])
interface CoffeePriceComponent {

    fun inject(mainActivity: MainActivity)

}