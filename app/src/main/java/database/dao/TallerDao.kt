package database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import database.entities.Taller


/**
 ****Creado por: Edison Martinez,
 ****Fecha:05,Wednesday,2022,
 ****Hora: 9:45 PM.
 **/
@Dao
interface TallerDao
{
    @Query("Select * from taller")
    suspend fun findAll():List<Taller>

    @Insert
    suspend fun save(taller: Taller)

}//Fin del a interfaz TallerDao