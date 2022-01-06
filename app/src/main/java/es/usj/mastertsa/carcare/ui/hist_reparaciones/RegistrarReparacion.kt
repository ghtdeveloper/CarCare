package es.usj.mastertsa.carcare.ui.hist_reparaciones

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
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



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrarReparacionBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }







}//Fin de la class RegistrarReparacion
