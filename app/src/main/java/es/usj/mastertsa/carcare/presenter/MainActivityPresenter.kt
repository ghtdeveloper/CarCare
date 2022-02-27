package es.usj.mastertsa.carcare.presenter

import androidx.fragment.app.FragmentManager
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.view.ui.hist_reparaciones.FragmentoHistReparaciones
import es.usj.mastertsa.carcare.view.ui.talleres.FragmentoTalleres
import es.usj.mastertsa.carcare.view.ui.vehiculos.FragmentoVehiculos


/**
 ****Creado por: Edison Martinez,
 ****Fecha:17,Thursday,2022,
 ****Hora: 8:54 PM.
 **/
class MainActivityPresenter (_view: ContractInterface.ViewMainActivity) : ContractInterface.
                                PresenterMainActivity
{
    private var view: ContractInterface.ViewMainActivity = _view
    //Fragments
    private lateinit var FragmentoVehiculos : FragmentoVehiculos
    private lateinit var FragmentoHistReparaciones : FragmentoHistReparaciones
    private lateinit var FragmentoTalleres : FragmentoTalleres

    init {
        view.initView()
    }

    override fun showFragmentVehicle(fragmentManager: FragmentManager)
    {
        FragmentoVehiculos = FragmentoVehiculos()
        fragmentManager.beginTransaction().
        replace(R.id.fragment_container_view,
            FragmentoVehiculos).commit()
    }

    override fun showFragmentRepair(fragmentManager: FragmentManager)
    {
        FragmentoHistReparaciones = FragmentoHistReparaciones()
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            FragmentoHistReparaciones).commit()
    }

    override fun showFragmentWorkShop(fragmentManager: FragmentManager)
    {
        FragmentoTalleres = FragmentoTalleres()
        fragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            FragmentoTalleres).commit()
    }

}