package es.usj.mastertsa.carcare.repositories.room.tallerrepository

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