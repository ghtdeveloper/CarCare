package es.usj.mastertsa.carcare.ui.hist_reparaciones

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import application.App
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.google.android.material.textfield.TextInputEditText
import database.entities.Reparacion
import database.entities.Taller
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptador.AdaptadorReparacion
import es.usj.mastertsa.carcare.databinding.FragmentoHistReparacionesBinding
import es.usj.mastertsa.carcare.tools.DatePickerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.ArrayList


class FragmentoHistReparaciones : Fragment(), AdaptadorReparacion.onClickItemReparacion {

    //Vista
    private lateinit var bindings : FragmentoHistReparacionesBinding
    //Variables y objetos
    private lateinit var selectFechaEntrada : String
    private lateinit var selectFechaSalida : String
    private lateinit var dataList : ArrayList<Reparacion>
    private  lateinit var dataListTalleres : ArrayList<Taller>
    private lateinit var dataListNombreTaller : ArrayList<String>
    private  lateinit var dataListVehiculos : ArrayList<Vehiculo>
    private lateinit var dataListNombreVehiculos : ArrayList<String>
    private lateinit var adaptadorReparacion: AdaptadorReparacion
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var  alertDialog : AlertDialog
    private lateinit var valueSelectSpinnerTaller : String
    private lateinit var valueSelectSpinnerVehiculo : String
    private lateinit var spinnerTaller : SmartMaterialSpinner<String>
    private lateinit var spinnerVehiculo : SmartMaterialSpinner<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        bindings =  FragmentoHistReparacionesBinding.inflate(inflater, container,
            false)
        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,
        false)
        bindings.recyclerViewReparacion.layoutManager = linearLayoutManager
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        bindings.floatingActionButton.setOnClickListener { loadRegistrarReparacion() }
        super.onViewCreated(view, savedInstanceState)
    }//Fin del metodo onViewCreated

    @Override
    override fun onStart() {
        super.onStart()
        loadRecyclerView()
    }

    private fun loadRecyclerView()
    {
        dataList = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                //Call database
                dataList.addAll(App.getDb().reparacionDao().findAll())
                if(App.getDb().reparacionDao().findAll().isEmpty())
                {
                    Log.d("INFO","NO EXISTE DATOS EN LA ENTIDAD REPARACION")
                    activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                        bindings.imageView.visibility = View.VISIBLE
                        bindings.textViewReparacion.visibility =View.VISIBLE
                        bindings.textViewReparacion.setText(R.string.text_info_reparacion_found)
                    })

                }else
                {
                    Log.d("INFO","EXISTE DATOS EN LA ENTIDAD REPARACION")
                    activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                        bindings.imageView.visibility = View.INVISIBLE
                        bindings.textViewReparacion.visibility =View.INVISIBLE
                        //Cargar Adaptador
                        adaptadorReparacion = AdaptadorReparacion(dataList)
                        adaptadorReparacion.setOnClick(this@FragmentoHistReparaciones)
                        bindings.recyclerViewReparacion.adapter = adaptadorReparacion
                    })
                }
            }
        }
    }//Fin del metodo loadRecyclerView

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
        val btnGuardar = dialogView.findViewById(R.id.saveButton) as Button
        val textTipoServicio = dialogView.findViewById(R.id.textInputTipoServicio) as
                TextInputEditText
        val textNota = dialogView.findViewById(R.id.textInputNotas) as TextInputEditText
        spinnerTaller = dialogView.findViewById(R.id.spinnerTalleres) as
                SmartMaterialSpinner<String>
        spinnerVehiculo = dialogView.findViewById(R.id.spinerVehiculo) as
                SmartMaterialSpinner<String>
        //Load Spinner
        cargarSpinnerTaller()
        cargarSpinnerVehiculo()
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
        btnGuardar.setOnClickListener {
            //Validaciones de que todas las informaciones esten completadas
            if(texFechaSalida.text.isNullOrEmpty() || textFechaEntrada.text.isNullOrEmpty() ||
                textTipoServicio.text.isNullOrEmpty() || textNota.text.isNullOrEmpty())
            {
                Toast.makeText(requireContext(),R.string.text_No_Campos_vacios,Toast.LENGTH_LONG)
                    .show()
            }
            else
            {
                //Se guarda la data
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        //LLamar a la base de datos desde una corutina
                        App.getDb().reparacionDao().save(Reparacion
                            (fechaEntrada = selectFechaEntrada, fechaSalida = selectFechaSalida,
                            notas = textNota.text.toString(),
                            tipoServicio = textTipoServicio.text.toString(),
                            talerNombre = valueSelectSpinnerTaller,
                            vehiculoInfo = valueSelectSpinnerVehiculo))
                    }
                }
                Toast.makeText(requireContext(),"Reparación registrada",Toast.LENGTH_LONG)
                    .show()
                //Se cierra el dialogo
                alertDialog.dismiss()
                //Se carga el recyclerView nuevamente
                loadRecyclerView()
            }
        }
        listenerSpinner()
        alertDialog = dialogBuilder.create()
        alertDialog.setTitle("Registrar Reparación")
        alertDialog.show()
    }//Fin de la funcion loadRegistrarReparacion


    private fun listenerSpinner()
    {
        spinnerTaller.onItemSelectedListener = object :AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
            {
                valueSelectSpinnerTaller = dataListNombreTaller[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

        spinnerVehiculo.onItemSelectedListener = object :AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
            {
                valueSelectSpinnerVehiculo = dataListNombreVehiculos[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }//Fin de la funcion listenerSpinner()

    private fun cargarSpinnerTaller()
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
                    spinnerTaller.item = dataListNombreTaller
                }
            }
        }
    }//Fin de la funcioncargarSpinnerVehiculo


    private fun cargarSpinnerVehiculo()
    {
        dataListNombreVehiculos = ArrayList()
        dataListVehiculos = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                dataListVehiculos.addAll(App.getDb().vehiculoDao().findAll())
                dataListVehiculos.forEach {
                    dataListNombreVehiculos.add(it.marca+"-"+it.modelo+"-"+it.colorVehiculor)
                    spinnerVehiculo.item = dataListNombreVehiculos
                }
            }
        }
    }//Fin de la funcioncargarSpinnerVehiculo


    override fun onClick(postion: Int)
    {
        val fm = activity?.supportFragmentManager
        val fragmentoReparacion = RegistrarReparacion()
        if (fm != null) {
            val bundle = Bundle()
            bundle.putString("notasGet",adaptadorReparacion.getNotas())
            bundle.putString("fechaEntradaGet",adaptadorReparacion.getFechaEntrada())
            bundle.putString("fechSalidaGet",adaptadorReparacion.getFechaSalida())
            bundle.putString("tipoServicio",adaptadorReparacion.getTipoServicio())
            bundle.putString("nombreTaller",adaptadorReparacion.getTallerNombre())
            bundle.putString("infoVehiculo",adaptadorReparacion.getInfoVehiculo())
            bundle.putLong("id",adaptadorReparacion.getID())
            fragmentoReparacion.arguments = bundle
            fragmentoReparacion.show(fm,"fragmentoReparacion")
        }
    }//Fin del metodo onClick

}//Fin del FragmentoHistReparaciones