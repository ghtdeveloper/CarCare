package es.usj.mastertsa.carcare.repositories.tallerrepository

import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.repositories.dao.TallerDao


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:04 PM.
 **/
class TallerRepository(private val tallerDao: TallerDao) :ITallerRepository
{
    override fun findAll(): List<Taller> {
        return tallerDao.findAll()
    }

    override fun save(taller: Taller) {
        tallerDao.save(taller)
    }

}