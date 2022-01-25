package es.usj.mastertsa.carcare.ui.vehiculos

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R

class VehiculosAdapter(val vehiculos: List<Vehiculo>, private val context: Context):RecyclerView.Adapter<VehiculosAdapter.VehiculoHolder>() {

    //    lateinit var vehiculoInfo : TextView
//    lateinit var vehiculoChasis : TextView
//    lateinit var VehiculoTipo : TextView

    private lateinit var listener: VehiculosAdapter.onClickItemVehiculo

     lateinit var marca : String
     lateinit var modelo : String
     lateinit var anioFabricacion : String
     lateinit var color : String
     lateinit var chasis : String
     lateinit var alias : String
      var id : Long = 0

    interface onClickItemVehiculo
    {
        fun onClick()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoHolder {
//        vehiculoInfo = view
//
//        init {
//            // Define click listener for the ViewHolder's View.
//            textView = view.findViewById(R.id.textView)
//        }
        val layoutInflater = LayoutInflater.from(parent.context)
        return VehiculoHolder(layoutInflater.inflate(R.layout.list_item_vehiculos,parent, false ))

    }

    override fun onBindViewHolder(holder: VehiculoHolder, position: Int) {
        val vehiculoModel = vehiculos.get(position)

        holder.render(vehiculos[position])

    }

    override fun getItemCount(): Int = vehiculos.size

    inner class VehiculoHolder(val view: View):RecyclerView.ViewHolder(view){

        fun render(vehiculo: Vehiculo){

//            val context : Context = Activity()
            view.findViewById<TextView>(R.id.txtViewChasis).text = vehiculo.chasis
            view.findViewById<TextView>(R.id.txtViewTipoVehiculo).text = vehiculo.colorVehiculor
            view.findViewById<TextView>(R.id.txtViewInfoVehiculo).text = if (!vehiculo.alias.isNullOrBlank())  vehiculo.alias else  vehiculo.marca + " " +vehiculo.modelo
//            view.findViewById<>()
            view.setOnClickListener {

                marca = vehiculo.marca
                modelo = vehiculo.modelo
                anioFabricacion = vehiculo.anioFabricacion
                color = vehiculo.colorVehiculor
                chasis = vehiculo.chasis
                alias = if (!vehiculo.alias.isNullOrBlank())  vehiculo.alias else " "
                id = vehiculo.id
                listener.onClick()

            }
        }
    }

    fun setOnClick(onClick: VehiculosAdapter.onClickItemVehiculo)
    {
        listener = onClick
    }//Fin de la funcion setOnClick

    //fun
}