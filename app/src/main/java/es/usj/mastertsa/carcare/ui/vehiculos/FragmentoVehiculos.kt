package es.usj.mastertsa.carcare.ui.vehiculos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import application.App
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptador.AdaptadorReparacion
import es.usj.mastertsa.carcare.databinding.FragmentoVehiculosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FragmentoVehiculos : Fragment(),VehiculosAdapter.onClickItemVehiculo {

    private lateinit var bindings : FragmentoVehiculosBinding
    private lateinit var registrarVehiculo: RegistrarVehiculo
    private lateinit var adapter: VehiculosAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindings =  FragmentoVehiculosBinding.inflate(inflater, container,  false)
        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,
            false)
        bindings.rvVehiculo.layoutManager = linearLayoutManager

        lifecycleScope.launch {
            val vehiculosList = withContext(Dispatchers.IO) {App.getDb().vehiculoDao().findAll()}
            //Verificar si hay data antes de cargar el recyclerview
            if(vehiculosList.isNullOrEmpty())
            {
                Log.d("NO DATA","No existe ningun vehiculo registrado")
                activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                    bindings.imageView.visibility = View.VISIBLE
                    bindings.textViewReparacion.visibility =View.VISIBLE
                    bindings.textViewReparacion.setText(R.string.text_info_vehiculo_found)
                })
            }else
            {
                Log.d("DATA","Existe vehiculos registrados")
                activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                    bindings.imageView.visibility = View.INVISIBLE
                    bindings.textViewReparacion.visibility =View.INVISIBLE
                    //Cargar Adaptador
                    initRecycler(vehiculosList)
                })
            }
        }
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindings.floatingActionButton.setOnClickListener {   loadRegistrarVehiculo()}
        super.onViewCreated(view, savedInstanceState)
    }

    fun initRecycler(list :MutableList<Vehiculo>) {

        bindings.rvVehiculo.layoutManager = LinearLayoutManager(activity)
        adapter = context?.let { VehiculosAdapter(list, it) }!!
        adapter.setOnClick(this)
        bindings.rvVehiculo.adapter = adapter

    }

    private fun loadRegistrarVehiculo()
    {
        val fm = activity?.supportFragmentManager
        val fragmentoVehiculo = RegistrarVehiculo()
        if (fm != null) {
            fragmentoVehiculo.show(fm,"fragmentoVehiculo")
        }
    }//Fin de la funcion loadRegistrarReparacion

    override fun onClick() {
        val fm = activity?.supportFragmentManager
        val fragmentoVehiculo = RegistrarVehiculo()
        if (fm != null) {
            val bundle = Bundle()
            bundle.putString("marca",adapter.marca)
            bundle.putString("modelo",adapter.modelo)
            bundle.putString("anioFabricacion",adapter.anioFabricacion)
            bundle.putString("color",adapter.color)
            bundle.putString("chasis",adapter.chasis)
            bundle.putString("alias",adapter.alias)
            bundle.putLong("id",adapter.id)
            fragmentoVehiculo.arguments = bundle
            fragmentoVehiculo.show(fm,"fragmentoVehiculo")
        }
    }

}