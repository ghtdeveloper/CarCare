package es.usj.mastertsa.carcare.view.ui.vehiculos

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptador.AdaptadorVehiculo
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.databinding.FragmentoVehiculosBinding
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.presenter.VehiculosActivityPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentoVehiculos : Fragment(), AdaptadorVehiculo.onClickItemVehiculo,AdaptadorVehiculo.onClickDeleteVehiculo
    ,ContractInterface.ViewFragmentVehicle
{

    private lateinit var bindings : FragmentoVehiculosBinding
    private lateinit var adapter: AdaptadorVehiculo
    private lateinit var linearLayoutManager: LinearLayoutManager
    //Presenter
    private val presenter: VehiculosActivityPresenter by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        bindings =  FragmentoVehiculosBinding.inflate(inflater, container,  false)
        linearLayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,
            false)
        bindings.rvVehiculo.layoutManager = linearLayoutManager

        lifecycleScope.launch {
            val vehiculosList = withContext(Dispatchers.IO) { presenter?.findAll()}
            //Verificar si hay data antes de cargar el recyclerview
            if(vehiculosList.isNullOrEmpty())
            {
                Log.d("NO DATA","No existe ningun vehiculo registrado")
                activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                    bindings.imageView.visibility = View.VISIBLE
                    bindings.textViewReparacion.visibility =View.VISIBLE
                    bindings.textViewReparacion.setText(R.string.text_info_vehiculo_found)
                })
            }else
            {
                Log.d("DATA","Existe vehiculos registrados")
                activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                    bindings.imageView.visibility = View.INVISIBLE
                    bindings.textViewReparacion.visibility =View.INVISIBLE
                    //Cargar Adaptador
                    initRecycler(vehiculosList)
                })
            }

        }
        return bindings.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        bindings.floatingActionButton.setOnClickListener {
            loadRegistrarVehiculo()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun initRecycler(list :MutableList<Vehiculo>)
    {
        bindings.rvVehiculo.layoutManager = LinearLayoutManager(activity)
        adapter = context?.let { AdaptadorVehiculo(list, it) }!!
        adapter.setOnClick(this)
        adapter.setOnClickDelete(this)
        bindings.rvVehiculo.adapter = adapter
    }

   override fun loadRegistrarVehiculo()
    {
        val fm = activity?.supportFragmentManager
        val fragmentoVehiculo = RegistrarVehiculo()
        if (fm != null) {
            val bundle = Bundle()
            bundle.putString("accion","registro_nuevo")
            fragmentoVehiculo.show(fm,"fragmentoVehiculo")
        }
    }

    override fun onClick() {
        val fm = activity?.supportFragmentManager
        val fragmentoVehiculo = RegistrarVehiculo()
        if (fm != null) {
            val bundle = Bundle()
            bundle.putString("marca",adapter.marca)
            bundle.putString("modelo",adapter.modelo)
            bundle.putString("anioFabricacion",adapter.anioFabricacion)
            bundle.putString("color",adapter.color)
            bundle.putString("chasis",adapter.chasis)
            bundle.putString("alias",adapter.alias)
            bundle.putLong("id",adapter.id)
            bundle.putString("typeOperation","editar")
            fragmentoVehiculo.arguments = bundle
            fragmentoVehiculo.show(fm,"fragmentoVehiculo")
        }
    }

    override fun onClickDelete()
    {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setCancelable(false)
        dialogBuilder.setTitle(R.string.textTituloDeleteVehiculo)
        dialogBuilder.setPositiveButton(R.string.textEliminarDialogo){_,i ->
            lifecycleScope.launch {
                withContext(Dispatchers.IO)
                {
                    if(adapter.id.equals(null))
                    {
                        Log.d("Vehiculos","Error al eliminar el vehiculo")
                    }
                    else
                    {
                        presenter.delete(adapter.id)

                    }
                }
            }
            Toast.makeText(requireContext(),R.string.textMessageVehiculoEliminado,
                Toast.LENGTH_SHORT).show()
        }
        dialogBuilder.setNegativeButton(R.string.text_cancelar) { _, i -> }//Cancelar
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }


}