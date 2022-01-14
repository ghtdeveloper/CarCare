package es.usj.mastertsa.carcare.adaptador

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import database.entities.Reparacion
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptadorVista.AdaptadorVistaReparacion
import es.usj.mastertsa.carcare.ui.hist_reparaciones.RegistrarReparacion


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
   private  var dataSelect : ArrayList<String> = ArrayList()
   //Variables
   private lateinit var notas :String
   private lateinit var fechaEntrada : String
   private lateinit var fechaSalida : String
   private lateinit var tipoServicio : String
   private lateinit var tallerNombre : String
   private  var id : Long = 0

    interface onClickItemReparacion
    {
        fun onClick(postion : Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorVistaReparacion
    {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_hist_reparaciones
       ,parent,false)
        return AdaptadorVistaReparacion(view)
    }//Fin del metodo onCreateViewHolder


    override fun onBindViewHolder(holder: AdaptadorVistaReparacion, position: Int)
    {
       val dataModal = dataSet.get(position)
        //Asignacion de informaciona vistas
        holder.txtViewReparacion.text = dataModal.notas
        holder.txtViewTallerReparacion.text = dataModal.talerNombre
        holder.textViewFechaSalida.text = dataModal.fechaSalida
        holder.cardviewHistReparacion.setOnClickListener {
            notas = dataSet[position].notas.toString()
            fechaEntrada = dataSet[position].fechaEntrada
            fechaSalida = dataSet[position].fechaSalida
            tipoServicio = dataSet[position].tipoServicio
            tallerNombre = dataSet[position].talerNombre
            id = dataSet[position].id
           listener.onClick(position)
        }

    }//Fin del metodo onBindViewHolder

    override fun getItemCount() =dataSet.size

    //Interfaz
    fun setOnClick(onClick: onClickItemReparacion)
    {
        listener = onClick
    }//Fin de la funcion setOnClick

    //fun

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


}//Fin de la class AdaptadorReparacion