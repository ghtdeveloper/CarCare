package es.usj.mastertsa.carcare.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.ui.principal.MainActivity

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    }//Fin del metodo onCreate

    override fun onStart()
    {
        super.onStart()
        cargarBienvenida()
    } //Fin del metodo onStart

    private fun cargarBienvenida()
    {
        Handler().postDelayed(
            {
                val mIntent = Intent(this, MainActivity::class.java)
                startActivity(mIntent)
                finish()
            }, 1100)
    }//Fin del metodo cargarBienvenida

}//Fin de la class SplashScreen
