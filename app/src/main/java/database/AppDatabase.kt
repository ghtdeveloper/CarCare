package database

import androidx.room.Database
import androidx.room.RoomDatabase
import database.dao.TallerDao
import database.dao.VehiculoDao
import database.entities.Taller
import database.entities.Vehiculo

@Database(
    entities = [Vehiculo::class,Taller::class],
    version = 1,
    exportSchema = false)

abstract class AppDatabase : RoomDatabase() {
    abstract fun vehiculoDao(): VehiculoDao
    abstract fun tallerDao() :  TallerDao
}