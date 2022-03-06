package es.usj.mastertsa.carcare.interactors.tallerinteractor

import es.usj.mastertsa.carcare.domain.Taller


/**
 ****Creado por: Edison Martinez,
 ****Fecha:26,Saturday,2022,
 ****Hora: 6:00 PM.
 **/
interface ITallerInteractor
{
    fun findAll():List<Taller>

    fun save(taller: Taller)

}