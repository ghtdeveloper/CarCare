package es.usj.mastertsa.carcare.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reparacion")
data class Reparacion (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val fechaEntrada: String,
    val fechaSalida: String,
    val notas: String?,
    val tipoServicio: String,
    val talerNombre: String,
    val vehiculoInfo : String
)