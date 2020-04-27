package tk.mohithaiyappa.coffeeprices.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.play.core.appupdate.AppUpdateManagerFactory
import com.google.android.play.core.install.model.ActivityResult
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
import tk.mohithaiyappa.coffeeprices.data.repository.CoffeePricesDataList
import javax.inject.Inject

private const val MY_REQUEST_CODE = 22

class MainActivity : AppCompatActivity() {

    private lateinit var newData: CoffeePricesDataList.Data
    private lateinit var oldData: CoffeePricesDataList.Data
    lateinit var adapter: RecyclerViewAdapter
    lateinit var mAdView : AdView
    private var compositeDisposable: CompositeDisposable? = null

    @Inject
    lateinit var service: CoffeePricesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CoffeeApplication).coffeePriceComponent.inject(this)

        checkForUpdate()
        getData()
        setupAdView()
    }

    private fun setupAdView() {

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    private fun getData() {
        val disposable = service.getAllPrices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val size = it.mData.lastIndex
                newData = it.mData[size]
                oldData = it.mData[size-1]
                setupRecyclerView()
                adapter.notifyDataSetChanged()
            }, {
                Log.e(
                    "jhgjh", "error", it
                )
            })
        compositeDisposable?.add(disposable)
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        adapter = RecyclerViewAdapter(newData, oldData, this)
        recycler_view.adapter = adapter
        recycler_view.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE
    }

    private fun checkForUpdate(){
        val appUpdateManager = AppUpdateManagerFactory.create(this)
        val appUpdateInfoTask = appUpdateManager.appUpdateInfo
        appUpdateInfoTask.addOnSuccessListener { appUpdateInfo ->
            if (appUpdateInfo.updateAvailability()==UpdateAvailability.UPDATE_AVAILABLE
                && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)){
                //request update
                appUpdateManager.startUpdateFlowForResult(appUpdateInfo,AppUpdateType.IMMEDIATE,this,MY_REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MY_REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Toast.makeText(this,"Update flow failed! Result code: $resultCode",Toast.LENGTH_SHORT).show()
                checkForUpdate()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }
}
