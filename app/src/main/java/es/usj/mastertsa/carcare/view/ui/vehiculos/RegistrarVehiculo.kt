package es.usj.mastertsa.carcare.view.ui.vehiculos

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarVehiculoBinding
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.presenter.FirebasePresenter
import es.usj.mastertsa.carcare.presenter.VehiculosActivityPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrarVehiculo : DialogFragment(), ContractInterface.ViewAddVehicle {

    private lateinit var bindings : FragmentRegistrarVehiculoBinding
    var vehiculo: Vehiculo? = null
    //presenter
    private val presenter: VehiculosActivityPresenter? by viewModel()
    private var presenterFirebase: FirebasePresenter? = null
    private lateinit var listMarks:ArrayList<String>
    private lateinit var valueSelectSpinnerVehiculo : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //OnCreate
        presenterFirebase = FirebasePresenter()
        if  (vehiculo != null) {
           // bindings.tvMarca.setText(vehiculo!!.marca)
            bindings.tvAlias.setText(vehiculo!!.alias)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
            bindings =  FragmentRegistrarVehiculoBinding.inflate(inflater, container,
                false)
            bindings.tvAlias.setText(arguments?.getString("alias"))
            bindings.tvModelo.setText(arguments?.getString("modelo"))
            bindings.tvAnioFabricacion.setText(arguments?.getString("anioFabricacion"))
            bindings.tvVIN.setText(arguments?.getString("chasis"))
            bindings.tvColor.setText(arguments?.getString("color"))
            //Log.d("DATA OPERACION",arguments?.getString("typeOperation").toString())
            if(arguments?.getString("typeOperation").toString() =="editar")
            {
                bindings.tvModelo.setBackgroundColor(Color.YELLOW)
                bindings.tvColor.setBackgroundColor(Color.YELLOW)
                bindings.saveButton.text = "Editar"
                bindings.saveButton.setOnClickListener {
                    lifecycleScope.launch {
                        withContext(Dispatchers.IO)
                        {
                           arguments?.getLong("id")?.let { it1 ->
                                presenter?.update(valueSelectSpinnerVehiculo,
                                    bindings.tvModelo.text.toString(),bindings.tvColor.text.toString(),
                                    it1)
                            }
                        }
                    }
                    dismiss()
                }
            }
            else
            {
                bindings.saveButton.setOnClickListener {
                    addVehicle()
                }
            }
        return bindings.root
    }

    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSpinnerMarcas()
    }

    override fun onResume() {
        super.onResume()

    //Dimensionar fragment
        val dialogWidth = dialog?.window?.attributes?.width ?: 1000
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, dialogWidth
        )
    }

    override fun addVehicle()
    {
        if(  bindings.tvModelo.text.isNullOrEmpty()
            ||bindings.tvAnioFabricacion.text.isNullOrEmpty() || bindings.tvColor.text.isNullOrEmpty()
            || bindings.tvVIN.text.isNullOrEmpty() || bindings.tvAlias.text.isNullOrEmpty())
        {
            Toast.makeText(context,"Debe completar los campos",Toast.LENGTH_LONG).show()

        }else
        {
            lifecycleScope.launch{
                withContext(Dispatchers.IO)
                {
                        presenter?.save(
                            Vehiculo( marca = valueSelectSpinnerVehiculo
                                ,modelo =bindings.tvModelo.text.toString(), anioFabricacion =
                                bindings.tvAnioFabricacion.text.toString()
                                ,color = bindings.tvColor.text.toString(),
                                chasis = bindings.tvVIN.text.toString(),
                                alias = bindings.tvAlias.text.toString())
                        )
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
    }

    override fun loadSpinnerMarcas() {
        listMarks = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                presenterFirebase!!.getListMarks().get().addOnSuccessListener { result ->
                    for (document in result) { listMarks.add(document.id) }
                    bindings.spinnerMarcaVehiculo.item = listMarks as List<Any>?
                    listMarks.forEachIndexed() { index,it ->
                        if(it==arguments?.getString("marca"))
                        {
                            setSelectionSpinnerTalleres(index)
                        }
                    }
                }
            }
        }
        listenerSpinner()
    }

    override fun setSelectionSpinnerTalleres(valoSet: Int) {
        bindings.spinnerMarcaVehiculo.setSelection(valoSet)
    }

    override fun listenerSpinner()
    {
        bindings.spinnerMarcaVehiculo.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long)
            {
                valueSelectSpinnerVehiculo = listMarks[position]
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

}