package database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import database.entities.Reparacion

@Dao
interface ReparacionDao {

    @Query("Select * from reparacion")
    fun findAll():MutableList<Reparacion>

    @Query("Select * FROM reparacion WHERE id = :idReparacion")
    fun findByID(idReparacion: Long): Reparacion

    @Insert
    fun save(reparacion: Reparacion)

    @Update
    fun update(reparacion: Reparacion   )
}