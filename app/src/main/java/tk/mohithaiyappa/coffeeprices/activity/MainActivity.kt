package tk.mohithaiyappa.coffeeprices.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import tk.mohithaiyappa.coffeeprices.CoffeeApplication
import tk.mohithaiyappa.coffeeprices.R
import tk.mohithaiyappa.coffeeprices.adapter.RecyclerViewAdapter
import tk.mohithaiyappa.coffeeprices.repository.CoffeePricesApi
import tk.mohithaiyappa.coffeeprices.repository.CoffeePricesDataList
import javax.inject.Inject


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

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }
}
