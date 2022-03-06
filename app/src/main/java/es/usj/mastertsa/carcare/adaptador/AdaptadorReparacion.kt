package es.usj.mastertsa.carcare.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.usj.mastertsa.carcare.domain.Reparacion
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.view.ui.adaptersui.AdaptadorVistaReparacion


/**
 ****Creado por: Edison Martinez,
 ****Fecha:11,Tuesday,2022,
 ****Hora: 9:07 PM.
 **/
class AdaptadorReparacion(private val dataSet: ArrayList<Reparacion>) :
    RecyclerView.Adapter<AdaptadorVistaReparacion>()
{
    //interfaz
   private lateinit var listener: onClickItemReparacion
   private lateinit var listenerDelete: onClickDeleteReparacion
   private  var dataSelect : ArrayList<String> = ArrayList()
   //Variables
   private lateinit var notas :String
   private lateinit var fechaEntrada : String
   private lateinit var fechaSalida : String
   private lateinit var tipoServicio : String
   private lateinit var tallerNombre : String
   private lateinit var infoVehiculo : String
   private  var id : Long = 0

    interface onClickItemReparacion
    {
        fun onClick(postion : Int)
    }

    interface onClickDeleteReparacion
    {
        fun onClickDelet()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorVistaReparacion
    {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_hist_reparaciones
       ,parent,false)
        return AdaptadorVistaReparacion(view)
    }


    override fun onBindViewHolder(holder: AdaptadorVistaReparacion, position: Int)
    {
        val dataModal = dataSet.get(position)
        holder.textViewValueInfoVehiculo.text = dataModal.vehiculoInfo
        holder.textViewValueFechaEntrada.text = dataModal.fechaEntrada
        holder.textViewValueNotaReparacion.text = dataModal.tipoServicio
        holder.cardviewHistReparacion.setOnClickListener {
            notas = dataSet[position].notas.toString()
            fechaEntrada = dataSet[position].fechaEntrada
            fechaSalida = dataSet[position].fechaSalida
            tipoServicio = dataSet[position].tipoServicio
            tallerNombre = dataSet[position].talerNombre
            infoVehiculo = dataSet[position].vehiculoInfo
            id = dataSet[position].id
           listener.onClick(position)
        }
        holder.fltBtnEliminarReparacion.setOnClickListener {
            id = dataSet[position].id
            listenerDelete.onClickDelet()
        }
    }

    override fun getItemCount() =dataSet.size

    fun setOnClick(onClick: onClickItemReparacion)
    {
        listener = onClick
    }

    fun setOnClickDelete(onClick:onClickDeleteReparacion)
    {
        listenerDelete = onClick
    }

    fun getNotas() : String
    {
        return notas
    }

    fun getFechaEntrada() : String
    {
        return fechaEntrada
    }

    fun getFechaSalida() : String
    {
        return fechaSalida
    }

    fun getTipoServicio() : String
    {
        return tipoServicio
    }

    fun getTallerNombre() : String
    {
        return tallerNombre
    }
    fun getID() : Long
    {
        return  id
    }

    fun getInfoVehiculo() : String
    {
        return infoVehiculo
    }

}