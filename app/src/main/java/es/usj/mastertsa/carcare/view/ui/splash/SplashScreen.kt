package es.usj.mastertsa.carcare.view.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.presenter.SplashActivityPresenter

class SplashScreen : AppCompatActivity(),ContractInterface.ViewSplashScreen
{
    //Object Presenter
    private var presenter : SplashActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        //Init presenter
        presenter = SplashActivityPresenter(this)

    }//Fin del metodo onCreate

    override fun onStart()
    {
        super.onStart()
        initView()
    } //Fin del metodo onStart

    override fun initView()
    {
        presenter?.showSplashScreen(this, this)

    }//Fin del metodo initView

}//Fin de la class SplashScreen
