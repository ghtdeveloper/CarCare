package es.usj.mastertsa.carcare.view.ui.adaptersui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import es.usj.mastertsa.carcare.R


/**
 ****Creado por: Edison Martinez,
 ****Fecha:06,Thursday,2022,
 ****Hora: 10:28 PM.
 **/
class AdaptadorVistaTaller(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    //Variables
    val chipTaller : Chip = itemView.findViewById(R.id.chipBtnTaller)

}//Fin de la class AdaptadorVistaTaller