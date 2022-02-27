package es.usj.mastertsa.carcare.adaptador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.usj.mastertsa.carcare.domain.Vehiculo
import es.usj.mastertsa.carcare.R

class AdaptadorVehiculo(val vehiculos: List<Vehiculo>, private val context: Context):RecyclerView.Adapter<AdaptadorVehiculo.VehiculoHolder>() {

    private lateinit var listener: onClickItemVehiculo
    private lateinit var listenerDelete: onClickDeleteVehiculo

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

    interface onClickDeleteVehiculo
    {
        fun onClickDelete()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehiculoHolder {

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
            view.findViewById<TextView>(R.id.textViewValueVIN).text = vehiculo.chasis
            view.findViewById<TextView>(R.id.textViewValueMarca).text = vehiculo.marca
            view.findViewById<TextView>(R.id.textViewValueModelo).text = vehiculo.modelo
            view.findViewById<TextView>(R.id.textViewValueAlias).text = vehiculo.alias
            view.findViewById<TextView>(R.id.textViewValueYear).text = vehiculo.anioFabricacion
            view.findViewById<FloatingActionButton>(R.id.fltBtnEliminarVehiculo).setOnClickListener {
                id = vehiculo.id
                listenerDelete.onClickDelete()
            }

            view.setOnClickListener {
                marca = vehiculo.marca
                modelo = vehiculo.modelo
                anioFabricacion = vehiculo.anioFabricacion
                color = vehiculo.color
                chasis = vehiculo.chasis
                alias = if (!vehiculo.alias.isNullOrBlank())  vehiculo.alias else " "
                id = vehiculo.id
                listener.onClick()

            }
        }
    }

    fun setOnClick(onClick: onClickItemVehiculo)
    {
        listener = onClick

    }

    fun setOnClickDelete(onClick:onClickDeleteVehiculo)
    {
        listenerDelete = onClick
    }

}