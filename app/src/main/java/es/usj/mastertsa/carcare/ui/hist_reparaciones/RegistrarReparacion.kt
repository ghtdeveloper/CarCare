package es.usj.mastertsa.carcare.ui.hist_reparaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import application.App
import database.entities.Taller
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarReparacionBinding
import es.usj.mastertsa.carcare.databinding.FragmentoTalleresBinding
import es.usj.mastertsa.carcare.ui.vehiculos.FragmentoVehiculos
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*


class RegistrarReparacion : DialogFragment()
{
    //Bindnings
    private lateinit var binding: FragmentRegistrarReparacionBinding
    //Objetos
    private  lateinit var dataListTalleres : ArrayList<Taller>
    private lateinit var dataListNombreTaller : ArrayList<String>
    private var indexPosition:Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrarReparacionBinding.inflate(layoutInflater)
        binding.txtFechaEntrada.setText(arguments?.getString("fechaEntradaGet"))
        binding.textInputNotas.setText(arguments?.getString("notasGet"))
        binding.txtFechaSalida.setText(arguments?.getString("fechSalidaGet"))
        binding.textInputTipoServicio.setText(arguments?.getString("tipoServicio"))
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarSpinnerTalleres()
        binding.saveButton.setOnClickListener { updateData() }
    }

    override fun onResume() {
        super.onResume()

//Dimensionar fragment
        var dialogWidth = dialog?.window?.attributes?.width ?: 1000
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, dialogWidth
        )
    }

    private fun updateData()
    {
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                arguments?.getLong("id")?.let {
                    App.getDb().reparacionDao().update(binding.textInputTipoServicio.text.toString(),
                        binding.textInputNotas.text.toString(), it)
                    dismiss()
                    val fragmentManager = childFragmentManager
                    fragmentManager.beginTransaction().
                    add(FragmentoHistReparaciones(),"fragmento")
                        .commit()
                }
            }
        }
    }//Fin de la funcion updateData

    private fun cargarSpinnerTalleres()
    {
        dataListNombreTaller = ArrayList()
        dataListTalleres = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                dataListTalleres.addAll(App.getDb().tallerDao().findAll())
                dataListTalleres.forEach {
                   // Log.d("DATA TALLERES FILTRADO",it.nombre)
                    dataListNombreTaller.add(it.nombre)
                   binding.spinnerTalleres.item = dataListNombreTaller as List<Any>?
                   dataListNombreTaller.forEachIndexed() { index,it ->
                       if(it==arguments?.getString("nombreTaller"))
                       {
                        //Log.d("Indice", index.toString())
                        setSelectionSpinner(index)
                       }
                   }
                }
            }
        }
    }//Fin de la funcioncargarSpinnerVehiculo

    private fun setSelectionSpinner(valoSet: Int)
    {
        binding.spinnerTalleres.setSelection(valoSet)
    }

}//Fin de la class RegistrarReparacion
