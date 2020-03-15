package tk.mohithaiyappa.coffeeprices.di.modules

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import tk.mohithaiyappa.coffeeprices.repository.CoffeePricesApi
import javax.inject.Named

@Module(includes = [ApiModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory, @Named("baseUrl") baseUrl: String): CoffeePricesApi {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .baseUrl(baseUrl)
            .build()
            .create(CoffeePricesApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun provideGsonConverter(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }
}