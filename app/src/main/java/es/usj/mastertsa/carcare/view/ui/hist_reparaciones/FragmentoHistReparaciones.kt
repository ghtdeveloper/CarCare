package es.usj.mastertsa.carcare.view.ui.hist_reparaciones

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
import com.chivorn.smartmaterialspinner.SmartMaterialSpinner
import com.google.android.material.textfield.TextInputEditText
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptador.AdaptadorReparacion
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.databinding.FragmentoHistReparacionesBinding
import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.presenter.HistRepActivityPresenter
import es.usj.mastertsa.carcare.util.DatePickerFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentoHistReparaciones : Fragment(), AdaptadorReparacion.onClickItemReparacion,
    AdaptadorReparacion.onClickDeleteReparacion, ContractInterface.ViewHistReparacion
{
    //Vista
    private lateinit var bindings : FragmentoHistReparacionesBinding
    //Variables y objetos
    private lateinit var selectFechaEntrada : String
    private lateinit var selectFechaSalida : String
    private lateinit var dataList : ArrayList<Reparacion>
    private lateinit var dataListNombreTaller : ArrayList<String>
    private lateinit var dataListNombreVehiculos : ArrayList<String>
    private lateinit var adaptadorReparacion: AdaptadorReparacion
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var  alertDialog : AlertDialog
    private lateinit var valueSelectSpinnerTaller : String
    private lateinit var valueSelectSpinnerVehiculo : String
    private lateinit var spinnerTaller : SmartMaterialSpinner<String>
    private lateinit var spinnerVehiculo : SmartMaterialSpinner<String>
    //Objetc Presenter
    private val presenter: HistRepActivityPresenter by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        bindings =  FragmentoHistReparacionesBinding.inflate(inflater, container,
            false)
        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,
        false)
        bindings.recyclerViewReparacion.layoutManager = linearLayoutManager
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initView()
    {
       bindings.floatingActionButton.setOnClickListener { showAddRepair() }
    }

    @Override
    override fun onStart()
    {
        super.onStart()
        loadRecyclerView()
    }

    override fun showAddRepair()
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
        loadSpinnerVehiculo()
        loadSpinnerTaller()
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
            if(texFechaSalida.text.isNullOrEmpty() || textFechaEntrada.text.isNullOrEmpty() ||
                textTipoServicio.text.isNullOrEmpty() || textNota.text.isNullOrEmpty())
            {
                Toast.makeText(requireContext(),R.string.text_No_Campos_vacios,Toast.LENGTH_LONG)
                    .show()
            }
            else
            {
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        presenter.save(Reparacion
                            (fechaEntrada = selectFechaEntrada, fechaSalida = selectFechaSalida,
                            notas = textNota.text.toString(),
                            tipoServicio = textTipoServicio.text.toString(),
                            talerNombre = valueSelectSpinnerTaller,
                            vehiculoInfo = valueSelectSpinnerVehiculo))
                    }
                }
                Toast.makeText(requireContext(),"Reparación registrada",Toast.LENGTH_LONG)
                    .show()
                alertDialog.dismiss()
               loadRecyclerView()
            }
        }
        listenerSpinner()
        alertDialog = dialogBuilder.create()
        alertDialog.setTitle("Registrar Reparación")
        alertDialog.show()
    }

    override fun loadSpinnerVehiculo()
    {
        lifecycleScope.launch{
            withContext(Dispatchers.IO)
            {
                spinnerVehiculo.item = presenter.loadSpinVehiculo()
                dataListNombreVehiculos = presenter.loadSpinVehiculo()
            }
        }
    }

    override fun loadSpinnerTaller()
    {
       lifecycleScope.launch {
           withContext(Dispatchers.IO)
           {
               spinnerTaller.item = presenter.loadSpinTaller()
               dataListNombreTaller = presenter.loadSpinTaller()
           }
       }
    }

    override fun listenerSpinner()
    {
        spinnerTaller.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
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
    }

    override fun loadRecyclerView() {
        dataList = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {

                if (presenter.findAll().isEmpty()) {
                    Log.d("INFO", "NO EXISTE DATOS EN LA ENTIDAD REPARACION")
                    activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                        bindings.imageView.visibility = View.VISIBLE
                        bindings.textViewReparacion.visibility = View.VISIBLE
                        bindings.textViewReparacion.setText(R.string.text_info_reparacion_found)
                    })

                } else {
                    dataList.addAll(presenter.findAll())
                    Log.d("INFO", "EXISTE DATOS EN LA ENTIDAD REPARACION")
                    activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                        bindings.imageView.visibility = View.INVISIBLE
                        bindings.textViewReparacion.visibility = View.INVISIBLE
                        //Cargar Adaptador
                        adaptadorReparacion = AdaptadorReparacion(dataList)
                        adaptadorReparacion.setOnClick(this@FragmentoHistReparaciones)
                        adaptadorReparacion.setOnClickDelete(this@FragmentoHistReparaciones)
                        bindings.recyclerViewReparacion.adapter = adaptadorReparacion
                    })
                }
            }
        }
    }
        override fun onClick(postion: Int) {
            val fm = activity?.supportFragmentManager
            val fragmentoReparacion = RegistrarReparacion()
            if (fm != null) {
                val bundle = Bundle()
                bundle.putString("notasGet", adaptadorReparacion.getNotas())
                bundle.putString("fechaEntradaGet", adaptadorReparacion.getFechaEntrada())
                bundle.putString("fechSalidaGet", adaptadorReparacion.getFechaSalida())
                bundle.putString("tipoServicio", adaptadorReparacion.getTipoServicio())
                bundle.putString("nombreTaller", adaptadorReparacion.getTallerNombre())
                bundle.putString("infoVehiculo", adaptadorReparacion.getInfoVehiculo())
                bundle.putLong("id", adaptadorReparacion.getID())
                fragmentoReparacion.arguments = bundle
                fragmentoReparacion.show(fm, "fragmentoReparacion")
            }
        }

    override fun onClickDelet()
    {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setCancelable(false)
        dialogBuilder.setTitle(R.string.textTituloDeleteReparacion)
        dialogBuilder.setPositiveButton(R.string.textEliminarDialogo){_,i ->
            lifecycleScope.launch {
                withContext(Dispatchers.IO)
                {
                    if(adaptadorReparacion.getID().equals(null))
                    {
                        Log.d("Reparacion","Error al eliminar la reparacion")
                    }
                    else
                    {
                        presenter.delete(adaptadorReparacion.getID())
                    }
                }
            }
            Toast.makeText(requireContext(),R.string.textMessageReparacionEliminado,
                Toast.LENGTH_SHORT).show()
            //Se carga el recyclerView
            loadRecyclerView()
        }
        dialogBuilder.setNegativeButton(R.string.text_cancelar) { _, i -> }//Cancelar
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}


