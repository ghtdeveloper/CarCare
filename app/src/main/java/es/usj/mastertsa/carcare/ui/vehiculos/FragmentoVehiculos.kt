package es.usj.mastertsa.carcare.ui.vehiculos

import android.app.AlertDialog
import android.os.Bundle
import android.os.Message
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import application.App
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentoVehiculosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoVehiculos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoVehiculos : Fragment(),VehiculosAdapter.onClickItemVehiculo {

    private lateinit var bindings : FragmentoVehiculosBinding
    private lateinit var registrarVehiculo: RegistrarVehiculo
    private lateinit var adapter: VehiculosAdapter

//    private lateinit var vehiculosList: MutableList<Vehiculo>

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
//        recyclerView.findViewById<RecyclerView>(R.id.rvVehiculo)
        arguments?.let {

        }

        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                //LLamar a la base de datos desde una corutina
//                App.getDb().vehiculoDao().save(Vehiculo(marca = "Hyundai", modelo = "Sonata", anioFabricacion = "2014", colorVehiculor = "gris", chasis = "1829384904JJ4O4J", alias = "LA ESTUFA"))
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindings =  FragmentoVehiculosBinding.inflate(inflater, container,  false)

        lifecycleScope.launch {

            val vehiculosList = withContext(Dispatchers.IO) {App.getDb().vehiculoDao().findAll()}
//            withContext(Dispatchers.IO){
//
//                //LLamar a la base de datos desde una corutina
//                val vehiculosList = App.getDb().vehiculoDao().findAll()
//
//
//            }
            initRecycler(vehiculosList)

        }

//        var vehiculo = Vehiculo(marca = "Hyundai", modelo = "Sonata", anioFabricacion = "2014", colorVehiculor = "gris", chasis = "1829384904JJ4O4J", alias = "LA ESTUFA")

//        vehiculosList = mutableListOf(vehiculo)
//        vehiculosList.add(vehiculo)
//        initRecycler(vehiculosList)
        return bindings.root    }

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

    private fun loadData() {
//TODO Load data
        val arrayAdapter:ArrayAdapter<*>


    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoVehiculos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoVehiculos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }


    }

    override fun onClick() {

//        Toast.makeText(context,"Aqui estamos",Toast.LENGTH_LONG).show()
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