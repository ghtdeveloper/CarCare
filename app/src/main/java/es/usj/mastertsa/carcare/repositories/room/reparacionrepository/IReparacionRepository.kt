package es.usj.mastertsa.carcare.repositories.room.reparacionrepository

import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:01 PM.
 **/
interface IReparacionRepository
{
    fun findAll():MutableList<Reparacion>

    fun save(reparacion: Reparacion)

    fun update(tipoServicio: String, notas: String, id: Long)

    fun findAllVehiculo():MutableList<Vehiculo>

    fun findAllTaller():MutableList<Taller>

    fun delete(idReparacion: Long)
}