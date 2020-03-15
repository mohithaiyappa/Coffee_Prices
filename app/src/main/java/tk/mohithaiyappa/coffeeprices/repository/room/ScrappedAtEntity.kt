package tk.mohithaiyappa.coffeeprices.repository.room

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class ScrappedAtEntity(
    @PrimaryKey
    var id : String,
    var scrapedAt:String
)