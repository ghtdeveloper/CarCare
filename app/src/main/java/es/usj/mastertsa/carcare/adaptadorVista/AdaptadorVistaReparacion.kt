package es.usj.mastertsa.carcare.adaptadorVista

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import es.usj.mastertsa.carcare.R


/**
 ****Creado por: Edison Martinez,
 ****Fecha:11,Tuesday,2022,
 ****Hora: 9:04 PM.
 **/
class AdaptadorVistaReparacion (itemview: View) : RecyclerView.ViewHolder(itemview)
{
    //Vistas
    val txtViewReparacion : TextView  = itemview.findViewById(R.id.txtViewReparacion)
    val txtViewTallerReparacion : TextView = itemview.findViewById(R.id.txtViewTallerReparacion)
    var textViewFechaSalida : TextView = itemview.findViewById(R.id.txtViewFechaSalida)
    val cardviewHistReparacion : CardView = itemview.findViewById(R.id.cardviewReparacion)

}//Fin de la class AdaptadorVistaReparacion