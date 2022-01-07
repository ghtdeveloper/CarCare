package es.usj.mastertsa.carcare.ui.talleres

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import application.App
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import database.AppDatabase
import database.entities.Taller
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptador.AdaptadorTaller
import es.usj.mastertsa.carcare.databinding.FragmentoTalleresBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class FragmentoTalleres : Fragment(), OnMapReadyCallback
{
    private lateinit var binding: FragmentoTalleresBinding
    //Objetcst
    private lateinit var adaptadorTaller: AdaptadorTaller
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dataList : ArrayList<Taller>
    private lateinit var mMap : GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentoTalleresBinding.inflate(layoutInflater)
        binding.floatingActionButton.setOnClickListener {
           mostrarRegistrarTaller()
        }
        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,
        false)
        binding.recyleViewTalleres.layoutManager = linearLayoutManager

        val mapFragment = childFragmentManager.findFragmentById(R.id.fragmap) as SupportMapFragment
        mapFragment.getMapAsync(this)
        return binding.root
    }

    @Override
    override fun onStart() {
        super.onStart()
        //Se carga la lista de talleres al iniciar el fragmento
        loadRecylerView()
    }

    private fun loadRecylerView()
    {
        dataList = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                //LLamar a la base de datos desde una corutina
                dataList.addAll(App.getDb().tallerDao().findAll())
            }
        }
        adaptadorTaller = AdaptadorTaller(dataList)
        binding.recyleViewTalleres.adapter = adaptadorTaller
    }//Fin del metodo loadRecylerView


    private fun mostrarRegistrarTaller()
    {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setCancelable(false)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_registar_taller,null)
        dialogBuilder.setView(dialogView)
        //Vistas
        val textNombreTaller = dialogView.findViewById(R.id.textInputNombreTaller) as
                TextInputEditText
        val textDireccionTaller = dialogView.findViewById(R.id.textInputDireccionTaller)
        as TextInputEditText
        //Buttons
        dialogBuilder.setPositiveButton(R.string.text_registrar) { _, i ->

            //Validacciones previa (no campos vacios)

            if(textNombreTaller.text.isNullOrEmpty() || textDireccionTaller.text.isNullOrEmpty())
            {
                Toast.makeText(requireContext(),R.string.text_No_Campos_vacios,Toast.LENGTH_LONG)
                    .show()
            }else
            {
                val nombre = textNombreTaller.text.toString()
                val direccion = textDireccionTaller.text.toString()
                lifecycleScope.launch {
               withContext(Dispatchers.IO){
                   //LLamar a la base de datos desde una corutina
                   App.getDb().tallerDao().save(Taller
                       (nombre = nombre, direccion = direccion, latitud = "18.4814581",
                   longitud = "-69.9697862"))
               }
                  }
                Toast.makeText(requireContext(),"Taller agregado exitosmente!",
                    Toast.LENGTH_LONG).show()
                //Se muestra la lista una vez se crea los talleres solo para refrescar
                loadRecylerView()
            }//Fin del else
        }//Aceptar
        dialogBuilder.setNegativeButton(R.string.text_cancelar) { _, i -> }//Cancelar
        val alertDialog = dialogBuilder.create()
        alertDialog.show()

    }//Fin del metodo mostrarRegistrarTaller

    override fun onMapReady(googleMap: GoogleMap)
    {
        mMap = googleMap
        val customLocation = LatLng(18.4814581, -69.9697862)
        mMap.addMarker(
            MarkerOptions()
            .position(customLocation)
            .title("Taller"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(customLocation,20f))
    }//Fin del metodo onMapReady


}//Fin de la class FragmentoTalleres