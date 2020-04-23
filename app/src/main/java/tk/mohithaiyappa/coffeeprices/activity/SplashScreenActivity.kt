package tk.mohithaiyappa.coffeeprices.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds
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

        disposable = Observable.timer(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()

    }
}
