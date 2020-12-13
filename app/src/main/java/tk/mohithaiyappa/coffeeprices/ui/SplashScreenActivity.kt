package tk.mohithaiyappa.coffeeprices.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash_screen.*
import tk.mohithaiyappa.coffeeprices.R
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {
    lateinit var disposable: Disposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        splashScreenImageView.run {
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
                val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this,splashScreenImageView,ViewCompat.getTransitionName(splashScreenImageView)!!)
                startActivity(intent,options.toBundle())
            }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
