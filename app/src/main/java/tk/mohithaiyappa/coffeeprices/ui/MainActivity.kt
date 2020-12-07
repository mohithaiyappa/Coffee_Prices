package tk.mohithaiyappa.coffeeprices.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.play.core.appupdate.AppUpdateManager
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.AppUpdateType
import com.google.android.play.core.install.model.UpdateAvailability
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import tk.mohithaiyappa.coffeeprices.CoffeeApplication
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.data.adapter.RecyclerViewAdapter
import tk.mohithaiyappa.coffeeprices.data.network.CoffeePricesApi
import java.text.SimpleDateFormat
import javax.inject.Inject

private const val MY_REQUEST_CODE = 22


class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter
    lateinit var mAdView : AdView
    lateinit var gridLayoutManager: GridLayoutManager
    private var compositeDisposable: CompositeDisposable? = null
    lateinit var appUpdateManager:AppUpdateManager
    val format = SimpleDateFormat("dd MMM yyyy hh:mm a")

    @Inject
    lateinit var service: CoffeePricesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progress_bar.visibility=View.VISIBLE

        (application as CoffeeApplication).coffeePriceComponent.inject(this)

        //recycler_view.layoutManager = GridLayoutManager(this,2)
        gridLayoutManager = GridLayoutManager(this, 2)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position == adapter.itemCount-1) 2 else 1
            }
        }
        recycler_view.layoutManager = gridLayoutManager
        recycler_view.setHasFixedSize(true)
        adapter = RecyclerViewAdapter()
        recycler_view.adapter = adapter

        checkForUpdates()
        getData()
        setupAdView()
    }

    private fun setupAdView() {
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun getData() {
        val disposable = service.getLatestPrice()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                progress_bar.visibility= View.VISIBLE
            }
            .doOnComplete {
                progress_bar.visibility= View.GONE
                recycler_view.visibility= View.VISIBLE
            }
            .doOnError {
                progress_bar.visibility= View.GONE
            }
            .subscribe({
                adapter.submitList(it.mData.sortedBy { it.priority })
                try {
                    it.mData.getOrNull(0)?.scrappedAt?.let { date ->
                        tvDate.text="Last Updated : ${format.format(date)}"
                    }
                }catch (e: Exception){
                    Log.e("date exception",e.toString())
                }

            }, {
                Log.e(
                    "jhgjh", "error", it
                )
            })
        compositeDisposable?.add(disposable)
    }

//    private fun setupRecyclerView(mData: List<LatestSpiceData.Data>) {
//       adapter.submitList(mData)
//        recycler_view.visibility = View.VISIBLE
//        progress_bar.visibility = View.GONE
//    }

    private fun checkForUpdates(){
        appUpdateManager =  AppUpdateManagerFactory.create(this)
        appUpdateManager.appUpdateInfo.addOnCompleteListener {
            if (it.isSuccessful) {
                val appUpdateInfo = it.result

                // Checks that the platform will allow the specified type of update.
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                    // For a flexible update, use AppUpdateType.FLEXIBLE
                    && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    // Request the update.

                    // Start an update.
                    appUpdateManager.startUpdateFlowForResult(
                        // Pass the intent that is returned by 'getAppUpdateInfo()'.
                        appUpdateInfo,
                        // Or 'AppUpdateType.FLEXIBLE' for flexible updates.
                        AppUpdateType.IMMEDIATE,
                        // The current activity making the update request.
                        this,
                        // Include a request code to later monitor this update request.
                        MY_REQUEST_CODE
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Toast.makeText(this,"Update flow failed! Result code: $resultCode",Toast.LENGTH_SHORT).show()
                checkForUpdates()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (recycler_view.visibility==View.VISIBLE){
            progress_bar.visibility=View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }

    override fun onBackPressed() {
        finish()
    }
}
