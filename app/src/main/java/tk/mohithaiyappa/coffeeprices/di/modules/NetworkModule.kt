package tk.mohithaiyappa.coffeeprices.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.mohithaiyappa.coffeeprices.data.network.CoffeePricesApi
//import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [ApiModule::class])
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        rxJava2CallAdapterFactory: RxJava2CallAdapterFactory
//        okHttpClient: OkHttpClient,
//        @Named("baseUrl") baseUrl: String
    ): CoffeePricesApi {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
//            .client(okHttpClient)
            .baseUrl("https://odd-pink-caiman-robe.cyclic.app/")
            .build()
            .create(CoffeePricesApi::class.java)
    }

    @Provides
    @Singleton
    fun providesGsonConverter(gson: Gson): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun providesRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

//    @Provides
//    @Singleton
//    fun providesOkHttpClient(): OkHttpClient {
//        return OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS).build()
//    }

    /**
     * Configures the reusable GSON singleton instance.
     */
    @Provides
    @Singleton
    fun gson(): Gson {
        val gsonBuilder = GsonBuilder()
        // set the server followed date format
        gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
        return gsonBuilder.create()
    }
}
