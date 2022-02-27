package es.usj.mastertsa.carcare.repositories.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo
import io.reactivex.Completable

@Dao
interface ReparacionDao {

  @Query("Select * from reparacion")
  fun findAll():MutableList<Reparacion>

  @Query("Select * from vehiculo")
  fun findAllVehiculo():MutableList<Vehiculo>

  @Query("Select * from taller")
  fun findAllTaller():MutableList<Taller>

  @Insert
  fun save(reparacion: Reparacion)

  @Query ("UPDATE reparacion SET tipoServicio = :tipoServicio, notas= :notas WHERE id =:id")
  fun update(tipoServicio: String, notas: String, id: Long)

  @Query("Delete FROM reparacion WHERE id = :idReparacion")
  fun delete(idReparacion: Long)

}