package es.usj.mastertsa.carcare.interactors.vehiculoInteractor

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import es.usj.mastertsa.carcare.domain.Vehiculo


/**
 ****Creado por: Edison Martinez,
 ****Fecha:26,Saturday,2022,
 ****Hora: 4:00 PM.
 **/
interface IVehiculoInteractor
{
    fun findAll():MutableList<Vehiculo>

    fun save(vehiculo: Vehiculo)

    fun update(marca:String,modelo:String,color:String,idVehiculo: Long)

    fun findByID(idVehiculo: Long): Vehiculo

    fun delete(idVehiculo: Long)

}