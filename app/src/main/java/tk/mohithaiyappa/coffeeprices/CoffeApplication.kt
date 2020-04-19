package tk.mohithaiyappa.coffeeprices

import android.app.Application
import com.google.android.gms.ads.MobileAds
import tk.mohithaiyappa.coffeeprices.di.CoffeePriceComponent
import tk.mohithaiyappa.coffeeprices.di.DaggerCoffeePriceComponent
import tk.mohithaiyappa.coffeeprices.di.modules.ContextModule

class CoffeeApplication: Application() {

    lateinit var coffeePriceComponent: CoffeePriceComponent
    var adMobAppID ="ca-app-pub-2357150776599621~8962927399"
    var bannerAdID = "ca-app-pub-2357150776599621/9348912538"
    var bannerAdIDTest = "ca-app-pub-3940256099942544/6300978111"

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}

        coffeePriceComponent = DaggerCoffeePriceComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}