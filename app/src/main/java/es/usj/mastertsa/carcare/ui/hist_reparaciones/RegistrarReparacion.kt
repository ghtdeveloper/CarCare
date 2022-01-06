package es.usj.mastertsa.carcare.ui.hist_reparaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarReparacionBinding
import es.usj.mastertsa.carcare.databinding.FragmentoTalleresBinding
import es.usj.mastertsa.carcare.ui.vehiculos.FragmentoVehiculos
import java.text.SimpleDateFormat
import java.util.*


class RegistrarReparacion : Fragment()
{
    //Bindnings
    private lateinit var binding: FragmentRegistrarReparacionBinding
    //FVariables
    private lateinit var selectFechaEnrada : String
    private lateinit var selectFechaSalida : String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrarReparacionBinding.inflate(layoutInflater)
        binding.textinputLayoutFechaEntrada.setOnClickListener {
            Toast.makeText(requireContext(),"Test",Toast.LENGTH_LONG).show()
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    /*
        Se obtiene la fecha del dia de hoy (actual)
     */
    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }

    fun getCurrentDate(): Date {
        return Calendar.getInstance().time
    }





}//Fin de la class RegistrarReparacion