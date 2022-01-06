package es.usj.mastertsa.carcare.ui.vehiculos

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentoVehiculosBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoVehiculos.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoVehiculos : Fragment() {

    private lateinit var bindings : FragmentoVehiculosBinding

    private lateinit var registrarVehiculo: RegistrarVehiculo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindings =  FragmentoVehiculosBinding.inflate(inflater, container,  false)
        return bindings.root    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindings.floatingActionButton.setOnClickListener {   loadRegistrarVehiculo()}

        super.onViewCreated(view, savedInstanceState)
    }


    private fun loadRegistrarVehiculo()
    {
//        registrarVehiculo = RegistrarVehiculo()
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.add(R.id.fragment_container_view,registrarVehiculo)?.commit()

        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_registrar_vehiculo,null)
        dialogBuilder.setView(dialogView)

        val alertDialog = dialogBuilder.create()
        alertDialog.setTitle("Añadir Vehiculo")
        alertDialog.show()

    }//Fin de la funcion loadRegistrarReparacion

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
}