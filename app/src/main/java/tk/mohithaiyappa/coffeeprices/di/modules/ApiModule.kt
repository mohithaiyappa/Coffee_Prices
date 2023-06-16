package tk.mohithaiyappa.coffeeprices.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
//import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Provides
    @Named("baseUrl")
    @Singleton
    fun provideBaseUrl() = "https://cropmarketprices-production.up.railway.app/"

}