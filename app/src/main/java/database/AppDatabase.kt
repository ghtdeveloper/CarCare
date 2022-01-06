package database

import androidx.room.Database
import androidx.room.RoomDatabase
import database.dao.VehiculoDao
import database.entities.Vehiculo

@Database(entities = [Vehiculo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehiculoDao(): VehiculoDao
}