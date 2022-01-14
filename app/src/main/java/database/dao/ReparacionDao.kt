package database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import database.entities.Reparacion
import database.entities.Taller

@Dao
interface ReparacionDao {

  @Query("Select * from reparacion")
    fun findAll():List<Reparacion>

  @Insert
  fun save(reparacion: Reparacion)

  @Query ("UPDATE reparacion SET tipoServicio = :tipoServicio, notas= :notas WHERE id =:id")
  fun update(tipoServicio: String, notas: String, id: Long)

}//Fin de la interfaz ReparacionDao