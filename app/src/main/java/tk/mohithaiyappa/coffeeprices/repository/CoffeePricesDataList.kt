package tk.mohithaiyappa.coffeeprices.repository


import com.google.gson.annotations.SerializedName

data class CoffeePricesDataList(
    @SerializedName("data")
    val mData: List<Data> = listOf(),
    val status: String = ""
) {
    data class Data(
        @SerializedName("_id")
        val id: String = "",
        val prices: List<Price> = listOf(),
        val scrappedAt: String = "",
        @SerializedName("__v")
        val v: Int = 0
    ) {
        data class Price(
            val spiceCost: String = "",
            val spiceName: String = ""
        )
    }
}