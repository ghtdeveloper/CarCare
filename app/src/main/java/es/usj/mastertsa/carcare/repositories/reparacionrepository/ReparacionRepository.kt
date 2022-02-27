package es.usj.mastertsa.carcare.repositories.reparacionrepository

import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.repositories.dao.ReparacionDao


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:02 PM.
 **/
class ReparacionRepository (private val reparacionDao: ReparacionDao) : IReparacionRepository
{
    override fun findAll(): MutableList<Reparacion> {
        return reparacionDao.findAll()
    }

    override fun save(reparacion: Reparacion) {
      reparacionDao.save(reparacion)
    }

    override fun update(tipoServicio: String, notas: String, id: Long) {
       reparacionDao.update(tipoServicio, notas, id)
    }

    override fun findAllVehiculo(): MutableList<Vehiculo> {
       return reparacionDao.findAllVehiculo()
    }

    override fun findAllTaller(): MutableList<Taller> {
       return reparacionDao.findAllTaller()
    }

    override fun delete(idReparacion: Long) {
        reparacionDao.delete(idReparacion)
    }


}

