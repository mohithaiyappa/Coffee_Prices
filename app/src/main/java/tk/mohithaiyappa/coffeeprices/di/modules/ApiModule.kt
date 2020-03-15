package tk.mohithaiyappa.coffeeprices.di.modules

import dagger.Module
import dagger.Provides
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import javax.inject.Named

@Module
class ApiModule {

    @Provides
    @Named("baseUrl")
    @ApplicationScope
    fun provideBaseUrl(): String {
        return "https://coffeemarketprice.herokuapp.com/"
    }
}