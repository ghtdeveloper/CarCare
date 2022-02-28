package es.usj.mastertsa.carcare.repositories.room.vehiculorepository

import com.google.firebase.firestore.CollectionReference
import es.usj.mastertsa.carcare.domain.Vehiculo


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:07 PM.
 **/
interface IVehiculoRepository
{
    fun findAll():MutableList<Vehiculo>

    fun save(vehiculo: Vehiculo)

    fun update(marca:String,modelo:String,color:String,idVehiculo: Long)

    fun findByID(idVehiculo: Long): Vehiculo

    fun delete(idVehiculo: Long)

}