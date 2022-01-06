package es.usj.mastertsa.carcare.ui.talleres

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.google.android.gms.maps.OnMapReadyCallback
import database.AppDatabase
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.databinding.FragmentoTalleresBinding
import java.text.SimpleDateFormat
import java.util.*


class FragmentoTalleres : Fragment()
{
    private lateinit var binding: FragmentoTalleresBinding
    //Objetcs Room
    private var room : AppDatabase? = null


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRoom()
    }

    private fun initRoom()
    {
        /*room = Room.databaseBuilder(requireContext(), AppDatabase::class.java,"taller").
        build()*/
        val room: AppDatabase = Room
            .databaseBuilder(requireContext(), AppDatabase::class.java, "people")
            .build()
    }//Fin del metodo initRoom()

    private fun mostrarRegistrarTaller()
    {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setCancelable(false)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_registar_taller,null)
        dialogBuilder.setView(dialogView)
        //Buttons
        dialogBuilder.setPositiveButton(R.string.text_registrar) { _, i ->

            Toast.makeText(requireContext(), "Soy el boton aceptar", Toast.LENGTH_LONG).show()


        }//Aceptar
        dialogBuilder.setNegativeButton(R.string.text_cancelar) { _, i -> }//Cancelar
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

    }//Fin del metodo mostrarRegistrarTaller



}//Fin de la class FragmentoTalleres