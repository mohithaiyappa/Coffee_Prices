package tk.mohithaiyappa.coffeeprices.di.modules

import android.app.Application
import dagger.Module
import dagger.Provides
import tk.mohithaiyappa.coffeeprices.di.scopes.ApplicationScope

@Module
class ContextModule(private val context: Application) {

    @Provides
    @ApplicationScope
    fun provideApplicationContext()=context
}