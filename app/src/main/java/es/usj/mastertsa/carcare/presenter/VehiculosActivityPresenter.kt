package es.usj.mastertsa.carcare.presenter

import androidx.lifecycle.ViewModel
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.interactors.vehiculoInteractor.IVehiculoInteractor


/**
 ****Creado por: Edison Martinez,
 ****Fecha:17,Thursday,2022,
 ****Hora: 8:59 PM.
 **/
class VehiculosActivityPresenter(private val iVehiculoInteractor: IVehiculoInteractor) :ContractInterface
.IVehiculoPresenter,ViewModel()
{
    init {
        findAll()
    }

    override fun findAll(): MutableList<Vehiculo>
    {
        return iVehiculoInteractor.findAll()
    }

    override fun save(vehiculo: Vehiculo)
    {
        iVehiculoInteractor.save(vehiculo)
    }

    override fun update(marca: String, modelo: String, color: String, idVehiculo: Long) {
        iVehiculoInteractor.update(marca, modelo, color, idVehiculo)
    }

    override fun findByID(idVehiculo: Long): Vehiculo
    {
       return iVehiculoInteractor.findByID(idVehiculo)
    }

    override fun delete(idVehiculo: Long) {
        iVehiculoInteractor.delete(idVehiculo)
    }

}