package es.usj.mastertsa.carcare.view.ui.adaptersui

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import es.usj.mastertsa.carcare.R


/**
 ****Creado por: Edison Martinez,
 ****Fecha:11,Tuesday,2022,
 ****Hora: 9:04 PM.
 **/
class AdaptadorVistaReparacion (itemview: View) : RecyclerView.ViewHolder(itemview)
{
    //Vistas
    val textViewValueInfoVehiculo : TextView  = itemview.findViewById(R.id.textViewValueInfoVehiculo)
    val textViewValueFechaEntrada : TextView = itemview.findViewById(R.id.textViewValueFechaEntrada)
    val textViewValueNotaReparacion : TextView = itemview.findViewById(R.id.textViewValueNotaReparacion)
    val fltBtnEliminarReparacion : FloatingActionButton = itemview.findViewById(R.id.fltBtnEliminarReparacion)
    val cardviewHistReparacion : CardView = itemview.findViewById(R.id.cardviewReparacion)

}//Fin de la class AdaptadorVistaReparacion