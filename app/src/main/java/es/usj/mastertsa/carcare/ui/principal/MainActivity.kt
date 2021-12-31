package es.usj.mastertsa.carcare.ui.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.ActivityMainBinding
import es.usj.mastertsa.carcare.ui.hist_reparaciones.FragmentoHistReparaciones
import es.usj.mastertsa.carcare.ui.talleres.FragmentoTalleres
import es.usj.mastertsa.carcare.ui.vehiculos.FragmentoVehiculos

class MainActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMainBinding
    //Fragmentos
    private lateinit var FragmentoVehiculos : FragmentoVehiculos
    private lateinit var FragmentoHistReparaciones : FragmentoHistReparaciones
    private lateinit var FragmentoTalleres : FragmentoTalleres

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Init
        initBottomNavegatiom()
    }//Fin del metodo onCreate

    private fun initBottomNavegatiom()
    {
        binding.bottomNavegation.setOnItemSelectedListener { item ->
            when(item.itemId)
            {
                R.id.menu_reparaciones -> { loadFragmetoHistReparaciones() }

                R.id.menu_vehiculos -> { loadFragmentoVehiculos() }

                R.id.menu_talleres -> { loadFragmentoTalleres() }
            }
            true
        }
    }//Fin de la funcion initBottomNavegation

    private fun loadFragmentoVehiculos()
    {
        FragmentoVehiculos = FragmentoVehiculos()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            FragmentoVehiculos).commit()
    }//Fin de la funcion loadFragmentoVehiculos

    private fun loadFragmetoHistReparaciones()
    {
        FragmentoHistReparaciones = FragmentoHistReparaciones()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            FragmentoHistReparaciones).commit()
    }//Fin de la funcion loadFragmentoVehiculos

    private fun loadFragmentoTalleres()
    {
        FragmentoTalleres = FragmentoTalleres()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container_view,
            FragmentoTalleres).commit()
    }//Fin de la funcion loadFragmentoVehiculos


}//Fin de la class MainActivity