package es.usj.mastertsa.carcare.app

import androidx.room.Room
import es.usj.mastertsa.carcare.interactors.reparacioninteractor.IReparacionInteractor
import es.usj.mastertsa.carcare.interactors.reparacioninteractor.ReparacionInteractor
import es.usj.mastertsa.carcare.interactors.tallerinteractor.ITallerInteractor
import es.usj.mastertsa.carcare.interactors.tallerinteractor.TallerInteractor
import es.usj.mastertsa.carcare.interactors.vehiculoInteractor.IVehiculoInteractor
import es.usj.mastertsa.carcare.interactors.vehiculoInteractor.VehiculoInteractor
import es.usj.mastertsa.carcare.presenter.HistRepActivityPresenter
import es.usj.mastertsa.carcare.presenter.TalleresActivityPresenter
import es.usj.mastertsa.carcare.presenter.VehiculosActivityPresenter
import es.usj.mastertsa.carcare.repositories.room.database.AppDatabase
import es.usj.mastertsa.carcare.repositories.room.reparacionrepository.IReparacionRepository
import es.usj.mastertsa.carcare.repositories.room.reparacionrepository.ReparacionRepository
import es.usj.mastertsa.carcare.repositories.room.tallerrepository.ITallerRepository
import es.usj.mastertsa.carcare.repositories.room.tallerrepository.TallerRepository
import es.usj.mastertsa.carcare.repositories.room.vehiculorepository.IVehiculoRepository
import es.usj.mastertsa.carcare.repositories.room.vehiculorepository.VehiculoRepository
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module


/**
 ****Creado por: Edison Martinez,
 ****Fecha:26,Saturday,2022,
 ****Hora: 4:53 PM.
 **/

val appModule = module {
    //DB
    single { Room.databaseBuilder(get(), AppDatabase::class.java, "carcare").build() }
    //Reparaciones
    single { get<AppDatabase>().reparacionDao() }
    single<IReparacionRepository> { ReparacionRepository(get()) }
    single<IReparacionInteractor> { ReparacionInteractor(get()) }
    viewModel { HistRepActivityPresenter(get()) }
    //Vehiculo
    single {get<AppDatabase>().vehiculoDao()}
    single<IVehiculoRepository> { VehiculoRepository(get()) }
    single<IVehiculoInteractor> { VehiculoInteractor(get()) }
    viewModel { VehiculosActivityPresenter(get()) }
    //Taller
    single {get<AppDatabase>().tallerDao()}
    single<ITallerRepository>{TallerRepository(get())}
    single<ITallerInteractor>{TallerInteractor(get())}
    viewModel { TalleresActivityPresenter(get()) }
    //Firebase
    //single ]}

}