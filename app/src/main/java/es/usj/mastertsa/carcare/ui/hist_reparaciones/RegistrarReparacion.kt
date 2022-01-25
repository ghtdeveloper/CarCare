package es.usj.mastertsa.carcare.ui.hist_reparaciones

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import application.App
import database.entities.Taller
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.databinding.FragmentRegistrarReparacionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class RegistrarReparacion : DialogFragment()
{
    //Bindnings
    private lateinit var binding: FragmentRegistrarReparacionBinding
    //Objetos
    private  lateinit var dataListTalleres : ArrayList<Taller>
    private lateinit var dataListNombreTaller : ArrayList<String>
    private  lateinit var dataListVehiculos : ArrayList<Vehiculo>
    private lateinit var dataListNombreVehiculos : ArrayList<String>
    private val REQUEST_IMAGE_CAPTURE = 1
    private val PERMISSION_REQUEST_CODE = 200
    private var indexPosition:Int = 0
    private lateinit var currentPathPhoton : String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrarReparacionBinding.inflate(layoutInflater)
        binding.txtFechaEntrada.setText(arguments?.getString("fechaEntradaGet"))
        binding.textInputNotas.setText(arguments?.getString("notasGet"))
        binding.txtFechaSalida.setText(arguments?.getString("fechSalidaGet"))
        binding.textInputTipoServicio.setText(arguments?.getString("tipoServicio"))
        binding.spinerVehiculo.isEnabled = false
        binding.spinnerTalleres.isEnabled = false
        binding.img1.setOnClickListener {
            initCamara()
        }
        binding.img2.visibility = View.INVISIBLE
        /*binding.img2.setOnClickListener {
            initCamara()
        }*/
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cargarSpinnerTalleres()
        cargarSpinnerVehiculo()
        binding.saveButton.setOnClickListener { updateData() }
    }

    override fun onResume() {
        super.onResume()

//Dimensionar fragment
        var dialogWidth = dialog?.window?.attributes?.width ?: 1000
        dialog?.window?.setLayout(
            resources.displayMetrics.widthPixels, dialogWidth
        )
    }

    private fun initCamara()
    {
        if(checkPermission())
        {
            //Si el permiso esta otorgado puedo continuar
            dispatchTakePictureIntent()
        }
        else
        {
            //De lo contrario solicito permiso
            requestPermission()
        }

    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also {
                takePictureIntent ->
            context?.let {
                takePictureIntent.resolveActivity(it.packageManager)?.also {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                }
            }
        }
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode ==RESULT_OK)
        {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            binding.img1.setImageBitmap(imageBitmap)
        }

    }

    private fun  checkPermission() : Boolean
    {
        if(context?.let {
                ContextCompat.checkSelfPermission(it,android.Manifest.permission.CAMERA)
            }
            != PackageManager.PERMISSION_GRANTED)
        {
            //Permiso no otorgado
            return false
        }
       return true
    }//Se chequea si el permiso esta otorgado

    //Se solicita el permiso
    private fun requestPermission()
    {
        ActivityCompat.requestPermissions(requireActivity(),
            arrayOf(android.Manifest.permission.CAMERA),
             PERMISSION_REQUEST_CODE)
    }

    private fun updateData()
    {
        lifecycleScope.launch {
            withContext(Dispatchers.IO)
            {
                arguments?.getLong("id")?.let {
                    App.getDb().reparacionDao().update(binding.textInputTipoServicio.text.toString(),
                        binding.textInputNotas.text.toString(), it)
                    dismiss()
                    val fragmentManager = childFragmentManager
                    fragmentManager.beginTransaction().
                    add(FragmentoHistReparaciones(),"fragmento")
                        .commit()
                }
            }
        }
    }//Fin de la funcion updateData

    private fun cargarSpinnerTalleres()
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
                   binding.spinnerTalleres.item = dataListNombreTaller as List<Any>?
                   dataListNombreTaller.forEachIndexed() { index,it ->
                       if(it==arguments?.getString("nombreTaller"))
                       {
                        //Log.d("Indice", index.toString())
                        setSelectionSpinnerTalleres(index)
                       }
                   }
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
                    // Log.d("DATA TALLERES FILTRADO",it.nombre)
                    dataListNombreVehiculos.add(it.marca+"-"+it.modelo+"-"+it.colorVehiculor)
                    binding.spinerVehiculo.item = dataListNombreVehiculos as List<Any>?
                    dataListNombreVehiculos.forEachIndexed() { index,it ->
                        if(it==arguments?.getString("infoVehiculo"))
                        {
                            setSelectionSpinnerVehiculos(index)
                        }
                    }
                }
            }
        }
    }//Fin de la funcioncargarSpinnerVehiculo


    private fun setSelectionSpinnerTalleres(valoSet: Int)
    {
        binding.spinnerTalleres.setSelection(valoSet)
    }


    private fun setSelectionSpinnerVehiculos(valoSet: Int)
    {
        binding.spinerVehiculo.setSelection(valoSet)
    }

}//Fin de la class RegistrarReparacion
