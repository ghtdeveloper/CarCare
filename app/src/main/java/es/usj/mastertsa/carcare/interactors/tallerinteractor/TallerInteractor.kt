package es.usj.mastertsa.carcare.interactors.tallerinteractor

import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.repositories.room.tallerrepository.ITallerRepository


/**
 ****Creado por: Edison Martinez,
 ****Fecha:26,Saturday,2022,
 ****Hora: 6:00 PM.
 **/
class TallerInteractor(private val iTallerRepository: ITallerRepository) : ITallerInteractor
{

    override fun findAll(): List<Taller> {
       return iTallerRepository.findAll()
    }

    override fun save(taller: Taller) {
      iTallerRepository.save(taller)
    }

}