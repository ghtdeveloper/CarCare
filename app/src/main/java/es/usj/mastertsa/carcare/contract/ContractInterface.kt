package es.usj.mastertsa.carcare.contract

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo


/**
 ****Creado por: Edison Martinez,
 ****Fecha:17,Thursday,2022,
 ****Hora: 8:47 PM.
 **/
interface ContractInterface
{
    //Vistas***************************************************************************************
    interface ViewSplashScreen
    {
        fun initView()
    }

    interface ViewMainActivity{
        fun initView()
    }

    interface ViewHistReparacion
    {
        fun initView()
        fun loadRecyclerView()
        fun showAddRepair()
        fun loadSpinnerVehiculo()
        fun loadSpinnerTaller()
        fun listenerSpinner()
    }

    interface ViewUpdtReparacion
    {
        fun updateData()
        fun loadSpinnerVehiculo()
        fun loadSpinnerTaller()
        fun setSelectionSpinnerTalleres(valoSet: Int)
        fun setSelectionSpinnerVehiculos(valoSet: Int)
    }

    interface ViewTalleres
    {
        fun loadRecylerView()
        fun showAddShopRepair()
    }

    interface ViewAddVehicle
    {
        fun addVehicle()
    }

    interface ViewFragmentVehicle
    {
        fun loadRegistrarVehiculo()
        fun initRecycler(list :MutableList<Vehiculo>)
    }




    //Presenter ***********************************************************************************

    interface PresenterMainActivity
    {
        fun showFragmentVehicle(fragmentManager: FragmentManager)
        fun showFragmentRepair(fragmentManager: FragmentManager)
        fun showFragmentWorkShop(fragmentManager: FragmentManager)
    }

    interface PresenterSplashActivity
    {
        fun showSplashScreen(context: Context, activity: Activity)
    }

   interface IReparacionPresenter
   {
       fun findAll():MutableList<Reparacion>

       fun save(reparacion: Reparacion)

       fun update(tipoServicio: String, notas: String, id: Long)

       fun  loadSpinVehiculo() : ArrayList<String>

       fun loadSpinTaller(): ArrayList<String>

       fun delete(idReparacion: Long)
   }

    interface IVehiculoPresenter
    {
        fun findAll():MutableList<Vehiculo>

        fun save(vehiculo: Vehiculo)

        fun update(marca:String,modelo:String,color:String,idVehiculo: Long)

        fun findByID(idVehiculo: Long): Vehiculo

        fun delete(idVehiculo: Long)

    }

    interface ITallerPresenter
    {
        fun findAll():List<Taller>

        fun save(taller: Taller)
    }

    interface IFirebasePresenter
    {
        //fun getListMarks(): CollectionReference?

        //fun getListMarksUpdt()
    }







}//Fin de la iterfaz ContractInterface