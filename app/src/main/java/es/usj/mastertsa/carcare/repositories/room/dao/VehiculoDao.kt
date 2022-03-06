package es.usj.mastertsa.carcare.repositories.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.carcare.domain.Vehiculo

@Dao
interface VehiculoDao {

    @Query("Select * from vehiculo")
    fun findAll():MutableList<Vehiculo>

    @Query("Select * FROM vehiculo WHERE id = :idVehiculo")
    fun findByID(idVehiculo: Long): Vehiculo

    @Insert
    fun save(vehiculo: Vehiculo)

    @Query("Update vehiculo set marca =:marca,modelo=:modelo,color=:color where id=:idVehiculo ")
    fun update(marca:String,modelo:String,color:String,idVehiculo: Long)

    @Query("Delete FROM vehiculo WHERE id = :idVehiculo")
    fun delete(idVehiculo: Long)
}