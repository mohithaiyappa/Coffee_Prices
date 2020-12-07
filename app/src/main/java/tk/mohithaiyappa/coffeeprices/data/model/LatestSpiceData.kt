package tk.mohithaiyappa.coffeeprices.data.model


import com.google.gson.annotations.SerializedName
import java.util.*

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
        var scrappedAt: Date? = null,
        @SerializedName("spiceCost")
        var spiceCost: String = "",
        @SerializedName("spiceName")
        var spiceName: String = "",
        @SerializedName("status")
        var status: Int = 0,
        @SerializedName("__v")
        var v: Int = 0,
        @SerializedName("priority")
        var priority :Int =-1,
        @SerializedName("graphData")
        var graphData: List<Data.GraphData> = listOf()
    ){
        data class GraphData(
            @SerializedName("_id")
            var id: String = "",
            @SerializedName("average")
            var average: Int=0,
            @SerializedName("scrappedAt")
            var scrappedAt: Date? = null
        )
    }
}