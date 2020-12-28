package tk.mohithaiyappa.coffeeprices

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.onesignal.OneSignal
import tk.mohithaiyappa.coffeeprices.di.CoffeePriceComponent
import tk.mohithaiyappa.coffeeprices.di.DaggerCoffeePriceComponent
import tk.mohithaiyappa.coffeeprices.di.modules.ContextModule

class CoffeeApplication: Application() {

    lateinit var coffeePriceComponent: CoffeePriceComponent
    var adMobAppID = "ca-app-pub-2357150776599621~8962927399"
    var bannerAdID = "ca-app-pub-2357150776599621/9348912538"
    var bannerAdIDTest = "ca-app-pub-3940256099942544/6300978111"

    private val ONESIGNAL_APP_ID = "ed6d45a1-3b1f-47ba-9990-155681e241c5"

    override fun onCreate() {
        super.onCreate()

        MobileAds.initialize(this) {}

        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)

        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)

        coffeePriceComponent = DaggerCoffeePriceComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }
}