package es.usj.mastertsa.carcare.view.ui.hist_reparaciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarReparacionBinding
import es.usj.mastertsa.carcare.presenter.HistRepActivityPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class RegistrarReparacion : DialogFragment(), ContractInterface.ViewUpdtReparacion
{
    //Bindnings
    private lateinit var binding: FragmentRegistrarReparacionBinding
    private lateinit var dataListNombreTaller : ArrayList<String>
    private lateinit var dataListNombreVehiculos : ArrayList<String>

    //Presenter
    private val presenter: HistRepActivityPresenter by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegistrarReparacionBinding.inflate(layoutInflater)
        binding.txtFechaEntrada.setText(arguments?.getString("fechaEntradaGet"))
        binding.textInputNotas.setText(arguments?.getString("notasGet"))
        binding.txtFechaSalida.setText(arguments?.getString("fechSalidaGet"))
        binding.textInputTipoServicio.setText(arguments?.getString("tipoServicio"))
        binding.spinerVehiculo.isEnabled = false
        binding.spinnerTalleres.isEnabled = false
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadSpinnerVehiculo()
        loadSpinnerTaller()
        binding.saveButton.setOnClickListener { updateData() }
    }

    override fun onResume() {
        super.onResume()

    //Dimensionar fragment
        val dialogWidth = dialog?.window?.attributes?.width ?: 1000
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, dialogWidth
        )
    }

    override fun updateData()
    {
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                arguments?.getLong("id")?.let {
                    presenter.update(binding.textInputTipoServicio.text.toString(),
                        binding.textInputNotas.text.toString(), it)
                    dismiss()
                    val fragmentManager = childFragmentManager
                    fragmentManager.beginTransaction().
                    add(FragmentoHistReparaciones(),"fragmento")
                        .commit()
                }
            }
        }
    }

    override fun loadSpinnerVehiculo() {
        lifecycleScope.launch{
            withContext(Dispatchers.IO)
            {
                binding.spinerVehiculo.item = presenter.loadSpinVehiculo() as List<Any>?
                dataListNombreVehiculos = presenter.loadSpinVehiculo()
                dataListNombreVehiculos.forEachIndexed() { index,it ->
                    if(it==arguments?.getString("infoVehiculo"))
                    {
                        setSelectionSpinnerVehiculos(index)
                    }
                }
            }
        }
    }

    override fun loadSpinnerTaller() {
        lifecycleScope.launch{
            withContext(Dispatchers.IO)
            {
                binding.spinnerTalleres.item = presenter.loadSpinTaller() as List<Any>?
                dataListNombreTaller = presenter.loadSpinTaller()
                dataListNombreTaller.forEachIndexed() { index,it ->
                    if(it==arguments?.getString("nombreTaller"))
                    {
                        setSelectionSpinnerTalleres(index)
                    }
                }
            }
        }
    }

    override fun setSelectionSpinnerTalleres(valoSet: Int)
    {
        binding.spinnerTalleres.setSelection(valoSet)
    }

    override fun setSelectionSpinnerVehiculos(valoSet: Int)
    {
        binding.spinerVehiculo.setSelection(valoSet)
    }

}
