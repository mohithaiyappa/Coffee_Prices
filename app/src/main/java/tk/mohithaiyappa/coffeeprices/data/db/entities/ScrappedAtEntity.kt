package tk.mohithaiyappa.coffeeprices.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class ScrappedAtEntity(
    @PrimaryKey
    var id : String,
    var scrapedAt:String
)