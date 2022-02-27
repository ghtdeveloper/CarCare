package es.usj.mastertsa.carcare.view.ui.talleres

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
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.textfield.TextInputEditText
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptador.AdaptadorTaller
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.databinding.FragmentoTalleresBinding
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.presenter.TalleresActivityPresenter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel


class FragmentoTalleres : Fragment(), OnMapReadyCallback, AdaptadorTaller.onClickItemTaller,
    ContractInterface.ViewTalleres
{
    private lateinit var binding: FragmentoTalleresBinding
    //Objetcst
    private lateinit var adaptadorTaller: AdaptadorTaller
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var dataList : ArrayList<Taller>
    private lateinit var mMap : GoogleMap
    //Presenter
    private val presenter: TalleresActivityPresenter? by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState:
        Bundle?
    ): View? {
        binding = FragmentoTalleresBinding.inflate(layoutInflater)
        binding.floatingActionButton.setOnClickListener {
           showAddShopRepair()
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
        loadRecylerView()
    }

    override fun loadRecylerView()
    {
        dataList = ArrayList()
        lifecycleScope.launch {
            withContext(Dispatchers.IO){
                //LLamar a la base de datos desde una corutina
                presenter?.findAll()?.let { dataList.addAll(it) }
                if(presenter?.findAll()?.isEmpty() == true)
                {

                    Log.d("INFO","NO EXISTE DATOS EN LA ENTIDAD TALLERES")
                    activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                        binding.horizontalScrollView.visibility = View.INVISIBLE
                        binding.constraintLayoutMap.visibility = View.INVISIBLE
                        binding.imageView.visibility = View.VISIBLE
                        binding.textViewInfoTaller.visibility =View.VISIBLE
                        binding.textViewInfoTaller.setText(R.string.text_info_taller_found)
                    })
                }
                else
                {
                    Log.d("INFO","EXISTE DATOS EN LA ENTIDAD TALLERES")
                    activity?.runOnUiThread(kotlinx.coroutines.Runnable {
                        binding.horizontalScrollView.visibility = View.VISIBLE
                        binding.constraintLayoutMap.visibility = View.VISIBLE
                        adaptadorTaller = AdaptadorTaller(dataList)
                        adaptadorTaller.setOnClick(this@FragmentoTalleres)
                        binding.recyleViewTalleres.adapter = adaptadorTaller
                        binding.textViewInfoTaller.visibility = View.INVISIBLE
                    })
                }
            }
        }
    }//Fin del metodo loadRecylerView

    override fun showAddShopRepair()
    {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setCancelable(false)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.dialog_registar_taller,null)
        dialogBuilder.setView(dialogView)
        val textNombreTaller = dialogView.findViewById(R.id.textInputNombreTaller) as
                TextInputEditText
        val textDireccionTaller = dialogView.findViewById(R.id.textInputDireccionTaller)
                as TextInputEditText
        dialogBuilder.setPositiveButton(R.string.text_registrar) { _, i ->
            if(textNombreTaller.text.isNullOrEmpty() || textDireccionTaller.text.isNullOrEmpty())
            {
                Toast.makeText(requireContext(),R.string.text_No_Campos_vacios,
                    Toast.LENGTH_LONG)
                    .show()
            }else
            {
                val nombre = textNombreTaller.text.toString()
                val direccion = textDireccionTaller.text.toString()
                lifecycleScope.launch {
                    withContext(Dispatchers.IO){
                        presenter?.save(Taller(nombre = nombre, direccion = direccion,
                            latitud = "18.4814581",
                            longitud = "-69.9697862"))
                    }
                }
                Toast.makeText(requireContext(),"Taller agregado exitosmente!",
                    Toast.LENGTH_LONG).show()
                loadRecylerView()
            }
        }
        dialogBuilder.setNegativeButton(R.string.text_cancelar) { _, i -> }//Cancelar
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    override fun onMapReady(googleMap: GoogleMap)
    {
        mMap = googleMap
        val customLocation = LatLng(18.4814581, -69.9697862)
        mMap.addMarker(
            MarkerOptions()
            .position(customLocation)
            .title("Taller"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(customLocation,20f))
    }

    override fun onClick(postion: Int) {}
}