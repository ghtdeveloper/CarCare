package application

import android.app.Application
import database.AppDatabase

class App : Application() {
    companion object {
        private var db: AppDatabase? = null
        public  fun getDb(): AppDatabase {
            return db!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDB(applicationContext)
    }
}