package database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import database.entities.Vehiculo

@Dao
interface VehiculoDao {

    @Query("Select * from vehiculo")
    fun findAll():List<Vehiculo>

    @Query("Select * FROM vehiculo WHERE ID = id")
    fun findByID(idVehiculo: Vehiculo)

    @Insert
    fun save(vehiculo: Vehiculo)

    @Update
    fun update(vehiculo: Vehiculo)
}