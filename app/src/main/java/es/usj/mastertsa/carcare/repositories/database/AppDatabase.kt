package es.usj.mastertsa.carcare.repositories.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.repositories.dao.ReparacionDao
import es.usj.mastertsa.carcare.repositories.dao.TallerDao
import es.usj.mastertsa.carcare.repositories.dao.VehiculoDao

@Database(entities = [Vehiculo::class, Taller::class, Reparacion::class], version = 1,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun reparacionDao() : ReparacionDao

    abstract fun vehiculoDao() : VehiculoDao

    abstract fun tallerDao() : TallerDao

}