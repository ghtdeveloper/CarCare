package database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 ****Creado por: Edison Martinez,
 ****Fecha:05,Wednesday,2022,
 ****Hora: 9:41 PM.
 **/
@Entity(tableName = "taller")
data class Taller(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val nombre : String,
    val direccion : String,
    val latitud : String,
    val longitud : String
)
