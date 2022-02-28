package es.usj.mastertsa.carcare.app

import android.app.Application
import org.koin.android.ext.android.startKoin


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