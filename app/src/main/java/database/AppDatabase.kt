package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import database.dao.TallerDao
import database.dao.VehiculoDao
import database.entities.Taller
import database.entities.Vehiculo
import java.security.AccessControlContext

@Database(entities = [Vehiculo::class,Taller::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {


    abstract fun vehiculoDao(): VehiculoDao
    abstract fun tallerDao() :  TallerDao


    companion object {
        private var db: AppDatabase? = null
        fun getDB(context: Context): AppDatabase {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, "database"
                ).build()
            }
            return db!!
        }
    }
}