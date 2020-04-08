package tk.mohithaiyappa.coffeeprices.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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
    lateinit var layoutManager: LinearLayoutManager
    private var compositeDisposable: CompositeDisposable? = null

    @Inject
    lateinit var service: CoffeePricesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as CoffeeApplication).coffeePriceComponent.inject(this)

        getData()
    }

    private fun getData() {
        val disposable = service.getAllPrices()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                newData = it.mData[0]
                oldData = it.mData[1]
                setupRecyclerView()
                adapter.notifyDataSetChanged()
            }
        compositeDisposable?.add(disposable)
    }

    private fun setupRecyclerView() {
        layoutManager = LinearLayoutManager(this)
        recycler_view.layoutManager = layoutManager
        recycler_view.setHasFixedSize(true)
        adapter = RecyclerViewAdapter(newData, oldData, this)
        recycler_view.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.dispose()
    }
}
