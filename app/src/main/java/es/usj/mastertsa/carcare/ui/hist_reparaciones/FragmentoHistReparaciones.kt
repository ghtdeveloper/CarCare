package es.usj.mastertsa.carcare.ui.hist_reparaciones

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentoHistReparacionesBinding
import es.usj.mastertsa.carcare.tools.DatePickerFragment
import java.text.SimpleDateFormat
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoHistReparaciones.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoHistReparaciones : Fragment() {


    private lateinit var bindings : FragmentoHistReparacionesBinding

    private lateinit var registrarReparacion: RegistrarReparacion
    //FVariables
    private lateinit var selectFechaEntrada : String
    private lateinit var selectFechaSalida : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment

//        floatingActionButton = view!!.findViewById(R.id.floatingActionButton)
        bindings =  FragmentoHistReparacionesBinding.inflate(inflater, container,  false)
        return bindings.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindings.floatingActionButton.setOnClickListener { loadRegistrarReparacion() }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun loadRegistrarReparacion()
    {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.fragment_registrar_reparacion,null)
        dialogBuilder.setView(dialogView)
         //Custom vista
        val textFechaEntrada = dialogView.findViewById(R.id.txtFechaEntrada)
                as EditText
        val texFechaSalida = dialogView.findViewById(R.id.txtFechaSalida) as EditText

        //Eventos
        textFechaEntrada.setOnClickListener {
            val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
                selectFechaEntrada= year.toString()+ "-" + (month + 1) + "-" + day
                 textFechaEntrada.setText(selectFechaEntrada)
            }
            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }
        texFechaSalida.setOnClickListener {
            val newFragment = DatePickerFragment.newInstance { _, year, month, day ->
                selectFechaSalida= year.toString()+ "-" + (month + 1) + "-" + day
                texFechaSalida.setText(selectFechaSalida)
            }
            newFragment.show(requireActivity().supportFragmentManager, "datePicker")
        }
        val alertDialog = dialogBuilder.create()
        alertDialog.setTitle("Registrar Reparación")
        alertDialog.show()
    }//Fin de la funcion loadRegistrarReparacion


    /*
        Se obtiene la fecha del dia de hoy (actual)

    fun Date.toString(format: String, locale: Locale = Locale.getDefault()): String {
        val formatter = SimpleDateFormat(format, locale)
        return formatter.format(this)
    }*/




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoHistReparaciones.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoHistReparaciones().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}