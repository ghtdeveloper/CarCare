package database.entities

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
    val tallerID: Long



)