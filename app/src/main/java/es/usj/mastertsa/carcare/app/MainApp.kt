package es.usj.mastertsa.carcare.app

import android.app.Application
import androidx.room.Room
import es.usj.mastertsa.carcare.interactors.reparacioninteractor.IReparacionInteractor
import es.usj.mastertsa.carcare.interactors.reparacioninteractor.ReparacionInteractor
import es.usj.mastertsa.carcare.interactors.vehiculoInteractor.IVehiculoInteractor
import es.usj.mastertsa.carcare.interactors.vehiculoInteractor.VehiculoInteractor
import es.usj.mastertsa.carcare.presenter.VehiculosActivityPresenter
import es.usj.mastertsa.carcare.repositories.database.AppDatabase
import es.usj.mastertsa.carcare.repositories.reparacionrepository.IReparacionRepository
import es.usj.mastertsa.carcare.repositories.reparacionrepository.ReparacionRepository
import es.usj.mastertsa.carcare.repositories.vehiculorepository.IVehiculoRepository
import es.usj.mastertsa.carcare.repositories.vehiculorepository.VehiculoRepository
import org.koin.android.ext.android.startKoin
import org.koin.androidx.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module


/**
 ****Creado por: Edison Martinez,
 ****Fecha:25,Friday,2022,
 ****Hora: 8:33 PM.
 **/
class MainApp : Application()
{
    @Override
    override fun onCreate()
    {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }

}