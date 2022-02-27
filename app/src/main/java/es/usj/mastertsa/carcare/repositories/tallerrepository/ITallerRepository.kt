package es.usj.mastertsa.carcare.repositories.tallerrepository

import androidx.room.Insert
import androidx.room.Query
import es.usj.mastertsa.carcare.domain.Taller


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:04 PM.
 **/
interface ITallerRepository
{
    fun findAll():List<Taller>

    fun save(taller: Taller)
}