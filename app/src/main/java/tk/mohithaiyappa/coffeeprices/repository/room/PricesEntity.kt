package tk.mohithaiyappa.coffeeprices.repository.room

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
@Entity(foreignKeys = [ForeignKey(entity = ScrappedAtEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("scrappedId"),
    onDelete = ForeignKey.CASCADE)]
)
data class PricesEntity(
    @PrimaryKey(autoGenerate = true)
    var pricesId : Long,
    var spiceName:String,
    var price:Int,
    var scrappedId: String,
    var hasDecreased:Boolean
)