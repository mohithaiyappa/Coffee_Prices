package tk.mohithaiyappa.coffeeprices.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.data.network.CoffeePricesApi
import tk.mohithaiyappa.coffeeprices.databinding.ActivityMainBinding
import tk.mohithaiyappa.coffeeprices.databinding.ActivitySplashScreenBinding
import java.util.concurrent.TimeUnit
import kotlin.Exception

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    lateinit var disposable: Disposable

    lateinit var binding: ActivitySplashScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.splashScreenImageView.run {
            alpha = 0f
            visibility = View.VISIBLE
            animate().alpha(1f).duration=600
        }

        lifecycle.addObserver(object : LifecycleEventObserver{
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event==Lifecycle.Event.ON_STOP){
                    lifecycle.removeObserver(this)
                    finish()
                }
            }

        })

        disposable = Observable.timer(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val intent = Intent(this, MainActivity::class.java)
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,binding.splashScreenImageView,ViewCompat.getTransitionName(binding.splashScreenImageView)!!)
                startActivity(intent,options.toBundle())
            }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()

    }
}
