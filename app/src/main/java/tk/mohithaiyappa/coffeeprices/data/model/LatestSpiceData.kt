package tk.mohithaiyappa.coffeeprices.data.model


import com.google.gson.annotations.SerializedName

data class LatestSpiceData(
    @SerializedName("data")
    var mData: List<Data> = listOf(),
    @SerializedName("status")
    var status: String = ""
) {
    data class Data(
        @SerializedName("average")
        var average: Int = 0,
        @SerializedName("_id")
        var id: String = "",
        @SerializedName("scrappedAt")
        var scrappedAt: String = "",
        @SerializedName("spiceCost")
        var spiceCost: String = "",
        @SerializedName("spiceName")
        var spiceName: String = "",
        @SerializedName("status")
        var status: Int = 0,
        @SerializedName("__v")
        var v: Int = 0
    )
}