package es.usj.mastertsa.carcare.repositories.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.carcare.domain.Taller


/**
 ****Creado por: Edison Martinez,
 ****Fecha:05,Wednesday,2022,
 ****Hora: 9:45 PM.
 **/
@Dao
interface TallerDao
{
    @Query("Select * from taller")
    fun findAll():List<Taller>

    @Insert
    fun save(taller: Taller)

}//Fin del a interfaz TallerDao