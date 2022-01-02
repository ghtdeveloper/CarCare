package es.usj.mastertsa.carcare.ui.talleres

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentoTalleresBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentoTalleres.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentoTalleres : Fragment() {

    private lateinit var binding: FragmentoTalleresBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        //Listener

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentoTalleresBinding.inflate(layoutInflater)
        binding.floatingActionButton.setOnClickListener {
           mostrarRegistrarTaller()
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentoTalleres.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentoTalleres().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun mostrarRegistrarTaller()
    {
        val dialoBuilder = AlertDialog.Builder(requireContext())
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_registar_taller,null)
        dialoBuilder.setView(dialogView)
        //Buttons
        dialoBuilder.setPositiveButton(R.string.text_registrar) { _, i ->
            Toast.makeText(requireContext(), "Soy el boton aceptar", Toast.LENGTH_LONG).show()
        }//Aceptar
        dialoBuilder.setNegativeButton(R.string.text_cancelar) { _, i -> }//Cancelar
        val alertDialog = dialoBuilder.create()
        alertDialog.show()

    }//Fin del metodo mostrarRegistrarTaller

}//Fin de la class FragmentoTalleres