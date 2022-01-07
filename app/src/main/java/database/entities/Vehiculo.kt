package database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculo")
data class Vehiculo (
@PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val marca: String,
    val modelo: String,
    val anioFabricacion: String,
    val colorVehiculor: String,
    val chasis: String,
    val alias: String?

    )