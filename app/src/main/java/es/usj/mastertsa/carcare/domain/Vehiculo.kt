package es.usj.mastertsa.carcare.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehiculo")
data class Vehiculo (
    @PrimaryKey(autoGenerate = true)
    val vehiculoId: Long = 0,
    val marca: String,
    val modelo: String,
    val anioFabricacion: String,
    val color: String,
    val chasis: String,
    val alias: String?
    )