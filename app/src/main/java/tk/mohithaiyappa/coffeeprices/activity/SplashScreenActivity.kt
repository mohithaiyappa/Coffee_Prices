package tk.mohithaiyappa.coffeeprices.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import tk.mohithaiyappa.coffeeprices.R
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {
    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Toast.makeText(this,"in splash activity", Toast.LENGTH_SHORT).show()

        disposable = Observable.timer(3, TimeUnit.SECONDS)
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
