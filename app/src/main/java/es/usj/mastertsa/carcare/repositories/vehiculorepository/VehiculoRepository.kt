package es.usj.mastertsa.carcare.repositories.vehiculorepository

import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.repositories.dao.VehiculoDao


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 7:08 PM.
 **/
class VehiculoRepository (private val vehiculoDao: VehiculoDao) : IVehiculoRepository
{
    override fun findAll(): MutableList<Vehiculo>
    {
       return  vehiculoDao.findAll()
    }

    override fun save(vehiculo: Vehiculo)
    {
        vehiculoDao.save(vehiculo)
    }

    override fun update(marca: String, modelo: String, color: String, idVehiculo: Long) {
      vehiculoDao.update(marca, modelo, color, idVehiculo)
    }

    override fun findByID(idVehiculo: Long): Vehiculo
    {
        return vehiculoDao.findByID(idVehiculo)
    }

    override fun delete(idVehiculo: Long) {
        vehiculoDao.delete(idVehiculo)
    }

}