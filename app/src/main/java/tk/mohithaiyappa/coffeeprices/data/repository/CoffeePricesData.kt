package tk.mohithaiyappa.coffeeprices.data.repository


import com.google.gson.annotations.SerializedName

data class CoffeePricesData(
    @SerializedName("data")
    var mdata: Data = Data(),
    var status: String = ""
) {
    data class Data(
        @SerializedName("_id")
        var id: String = "",
        var prices: List<Price> = listOf(),
        var scrappedAt: String = "",
        @SerializedName("__v")
        var v: Int = 0
    ) {
        data class Price(
            var spiceCost: String = "",
            var spiceName: String = ""
        )
    }
}