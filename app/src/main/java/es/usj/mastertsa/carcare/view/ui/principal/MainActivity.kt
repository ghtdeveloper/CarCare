package es.usj.mastertsa.carcare.view.ui.principal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.databinding.ActivityMainBinding
import es.usj.mastertsa.carcare.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(),ContractInterface.ViewMainActivity
{
    private lateinit var binding: ActivityMainBinding
    //Objects Presenter
    private var presenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Init Presenter
        FirebaseApp.initializeApp(this)
        presenter = MainActivityPresenter(this)
        initView()
    }

    override fun initView()
    {
        binding.bottomNavegation.setOnItemSelectedListener { item ->
             when(item.itemId)
             {
                 R.id.menu_reparaciones -> { presenter?.showFragmentRepair(this.
                 supportFragmentManager) }

                 R.id.menu_vehiculos -> { presenter?.
                 showFragmentVehicle(this.supportFragmentManager) }

                 R.id.menu_talleres -> { presenter?.showFragmentWorkShop(this.
                 supportFragmentManager) }
             }
             true
         }
    }

}