package es.usj.mastertsa.carcare.ui.vehiculos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import application.App
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarVehiculoBinding
import es.usj.mastertsa.carcare.databinding.FragmentoVehiculosBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class RegistrarVehiculo : DialogFragment() {

    private lateinit var bindings : FragmentRegistrarVehiculoBinding
    public var vehiculo: Vehiculo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            bindings.saveButton.setOnClickListener {
                registrarVehiculo()
            }
        return bindings.root
    }

    private fun registrarVehiculo()
    {
        if(bindings.tvMarca.text.isNullOrEmpty() ||  bindings.tvModelo.text.isNullOrEmpty()
            ||bindings.tvAnioFabricacion.text.isNullOrEmpty() || bindings.tvColor.text.isNullOrEmpty()
            || bindings.tvVIN.text.isNullOrEmpty() || bindings.tvAlias.text.isNullOrEmpty())
        {
            //Toast.makeText(context,"Debe completar los campos",Toast.LENGTH_LONG).show()


        }else
        {
            //Se registra el vehiculo
            lifecycleScope.launch {
                withContext(Dispatchers.IO)
                {
                    App.getDb().vehiculoDao().save(Vehiculo( marca = bindings.tvMarca.text.toString()
                    ,modelo =bindings.tvModelo.text.toString(), anioFabricacion =
                        bindings.tvAnioFabricacion.text.toString()
                    ,colorVehiculor = bindings.tvColor.text.toString(),
                    chasis = bindings.tvVIN.text.toString(),alias = bindings.tvAlias.text.toString()))
                }
                Toast.makeText(requireContext(),"Vehículo registrado exitosmente!",
                    Toast.LENGTH_LONG).show()
                //Se debe cerrar el dialogo y mostrar el recyclerview
                 dismiss()
                val fragmentoVehiculo = FragmentoVehiculos()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.fragment_container_view,fragmentoVehiculo,
                        "vehiculo_principal")?.commit()
            }
        }
    }//Fin del metodo registrarVehiculo

    override fun onResume() {
        super.onResume()

//Dimensionar fragment
        var dialogWidth = dialog?.window?.attributes?.width ?: 1000
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, dialogWidth
        )
    }


}//Fin de la clase RegistrarVehiculo