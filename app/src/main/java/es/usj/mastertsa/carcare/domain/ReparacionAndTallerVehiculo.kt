package es.usj.mastertsa.carcare.domain

import androidx.room.Embedded
import androidx.room.Relation


/**
 ****Creado por: Edison Martinez,
 ****Fecha:13,Sunday,2022,
 ****Hora: 7:06 PM.
 **/
data class ReparacionAndTallerVehiculo(
    @Embedded val taller: Taller,
    @Relation(
        parentColumn = "tallerId",
        entityColumn = "vehiculoId"
    )
    val vehiculo: Vehiculo
)
