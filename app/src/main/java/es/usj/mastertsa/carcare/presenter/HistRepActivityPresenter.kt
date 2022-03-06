package es.usj.mastertsa.carcare.presenter


import androidx.lifecycle.ViewModel
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.interactors.reparacioninteractor.IReparacionInteractor


/**
 ****Creado por: Edison Martinez,
 ****Fecha:17,Thursday,2022,
 ****Hora: 8:50 PM.
 **/
class HistRepActivityPresenter(private val iReparacionInteractor: IReparacionInteractor) :
    ContractInterface.IReparacionPresenter, ViewModel()
{
    //Objetcts
    private lateinit var dataListVehiculos : ArrayList<Vehiculo>
    private lateinit var dataListTalleres : ArrayList<Taller>

    init {
        findAll()
    }

    override fun findAll(): MutableList<Reparacion> {
       return iReparacionInteractor.findAll()
    }


    override fun save(reparacion: Reparacion)
    {
        iReparacionInteractor.save(reparacion)
    }

    override fun update(tipoServicio: String, notas: String, id: Long)
    {
        iReparacionInteractor.update(tipoServicio, notas, id)
    }

    override fun loadSpinVehiculo(): ArrayList<String>
    {
        dataListVehiculos = ArrayList()
        val dataListInfoVehiculo:ArrayList<String> = ArrayList()
        dataListVehiculos.addAll(iReparacionInteractor.findAllVehiculo())
        dataListVehiculos.forEach{
           dataListInfoVehiculo.add(it.marca+"-"+it.modelo+"-"+it.color)
        }
        return dataListInfoVehiculo
    }

    override fun loadSpinTaller(): ArrayList<String>
    {
        dataListTalleres = ArrayList()
        val dataListInfoTaller: ArrayList<String> = ArrayList()
        dataListTalleres.addAll(iReparacionInteractor.findAllTaller())
        dataListTalleres.forEach{
            dataListInfoTaller.add(it.nombre)
        }
        return dataListInfoTaller
    }

    override fun delete(idReparacion: Long) {
        iReparacionInteractor.delete(idReparacion)
    }

}