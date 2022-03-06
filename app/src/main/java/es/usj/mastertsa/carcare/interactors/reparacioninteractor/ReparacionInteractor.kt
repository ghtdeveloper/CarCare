package es.usj.mastertsa.carcare.interactors.reparacioninteractor

import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.repositories.room.reparacionrepository.IReparacionRepository


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:35 PM.
 **/
class ReparacionInteractor (private val iReparacionRepository: IReparacionRepository) :
    IReparacionInteractor
{
    override fun findAll(): MutableList<Reparacion> {
        return iReparacionRepository.findAll()
    }

    override fun save(reparacion: Reparacion) {
        iReparacionRepository.save(reparacion)
    }

    override fun update(tipoServicio: String, notas: String, id: Long) {
       iReparacionRepository.update(tipoServicio, notas, id)
    }

    override fun findAllVehiculo(): MutableList<Vehiculo> {
       return iReparacionRepository.findAllVehiculo()
    }

    override fun findAllTaller(): MutableList<Taller> {
      return iReparacionRepository.findAllTaller()
    }

    override fun delete(idReparacion: Long) {
        iReparacionRepository.delete(idReparacion)
    }

}