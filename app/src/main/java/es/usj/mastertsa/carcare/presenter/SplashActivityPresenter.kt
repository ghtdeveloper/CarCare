package es.usj.mastertsa.carcare.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.view.ui.principal.MainActivity
import java.util.*


/**
 ****Creado por: Edison Martinez,
 ****Fecha:17,Thursday,2022,
 ****Hora: 8:58 PM.
 **/
class SplashActivityPresenter(_viewSplashScreen: ContractInterface.ViewSplashScreen) :
                                ContractInterface.PresenterSplashActivity
{
    private var ViewSplashScreen: ContractInterface.ViewSplashScreen = _viewSplashScreen

    init {
        ViewSplashScreen.initView()
    }

    override fun showSplashScreen(context: Context, activity: Activity)
    {
        android.os.Handler().postDelayed(
            {
                val mIntent = Intent(context, MainActivity::class.java)
                activity.startActivity(mIntent)
                activity.finish()
            }, 1100)
    }

}