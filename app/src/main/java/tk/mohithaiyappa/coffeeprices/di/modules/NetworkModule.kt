package tk.mohithaiyappa.coffeeprices.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import tk.mohithaiyappa.coffeeprices.data.network.CoffeePricesApi
import java.util.concurrent.TimeUnit
import javax.inject.Named

@Module(includes = [ApiModule::class])
class NetworkModule {

    @Provides
    @ApplicationScope
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory,
                        rxJava2CallAdapterFactory :RxJava2CallAdapterFactory,
                        okHttpClient: OkHttpClient,
                        @Named("baseUrl") baseUrl: String): CoffeePricesApi {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .build()
            .create(CoffeePricesApi::class.java)
    }

    @Provides
    @ApplicationScope
    fun providesGsonConverter(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @ApplicationScope
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    @ApplicationScope
    fun providesOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build()
    }

    /**
     * Configures the reusable GSON singleton instance.
     */
    @ApplicationScope
    @Provides
    fun gson(): Gson {

        val gsonBuilder = GsonBuilder()
        // set the server followed date format
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        return gsonBuilder.create()
    }
}