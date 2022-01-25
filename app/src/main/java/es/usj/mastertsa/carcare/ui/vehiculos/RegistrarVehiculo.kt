package es.usj.mastertsa.carcare.ui.vehiculos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarVehiculoBinding
import es.usj.mastertsa.carcare.databinding.FragmentoVehiculosBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistrarVehiculo.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistrarVehiculo : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var bindings : FragmentRegistrarVehiculoBinding



    public var vehiculo: Vehiculo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        if  (vehiculo != null) {

            bindings.tvMarca.setText(vehiculo!!.marca)
            bindings.tvAlias.setText(vehiculo!!.alias)


        }
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        bindings =  FragmentRegistrarVehiculoBinding.inflate(inflater, container,  false)



            bindings.tvMarca.setText(arguments?.getString("marca"))
            bindings.tvAlias.setText(arguments?.getString("alias"))
            bindings.tvModelo.setText(arguments?.getString("modelo"))
            bindings.tvAnioFabricacion.setText(arguments?.getString("anioFabricacion"))
            bindings.tvVIN.setText(arguments?.getString("chasis"))
            bindings.tvColor.setText(arguments?.getString("color"))

        return bindings.root
    }

    override fun onResume() {
        super.onResume()

//Dimensionar fragment
        var dialogWidth = dialog?.window?.attributes?.width ?: 1000
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, dialogWidth
        )
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegistrarVehiculo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistrarVehiculo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}