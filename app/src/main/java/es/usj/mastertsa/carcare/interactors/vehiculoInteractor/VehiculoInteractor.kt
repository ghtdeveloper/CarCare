package es.usj.mastertsa.carcare.interactors.vehiculoInteractor

import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.repositories.vehiculorepository.IVehiculoRepository


/**
 ****Creado por: Edison Martinez,
 ****Fecha:26,Saturday,2022,
 ****Hora: 4:00 PM.
 **/
class VehiculoInteractor (private val iVehiculoRepository: IVehiculoRepository) :IVehiculoInteractor
{

    override fun findAll(): MutableList<Vehiculo> {
      return  iVehiculoRepository.findAll()
    }

    override fun save(vehiculo: Vehiculo) {
      iVehiculoRepository.save(vehiculo)
    }

    override fun update(marca: String, modelo: String, color: String, idVehiculo: Long) {
       iVehiculoRepository.update(marca, modelo, color, idVehiculo)
    }

    override fun findByID(idVehiculo: Long): Vehiculo {
        return iVehiculoRepository.findByID(idVehiculo)
    }

    override fun delete(idVehiculo: Long) {
       iVehiculoRepository.delete(idVehiculo)
    }

}