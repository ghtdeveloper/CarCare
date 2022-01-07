package es.usj.mastertsa.carcare.adaptador

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import database.entities.Taller
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.adaptadorVista.AdaptadorVistaTaller


/**
 ****Creado por: Edison Martinez,
 ****Fecha:06,Thursday,2022,
 ****Hora: 10:30 PM.
 **/
class AdaptadorTaller (private val dataSet: ArrayList<Taller>)  :
    RecyclerView.Adapter<AdaptadorVistaTaller>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorVistaTaller {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_talleres,
       parent,false)
        return AdaptadorVistaTaller(view)
    }//Fin del metodo onCreateViewHolder

    override fun onBindViewHolder(holder: AdaptadorVistaTaller, position: Int) {
       val dataModel =  dataSet.get(position)
        //Data
        holder.chipTaller.text = dataModel.nombre
    }

    override fun getItemCount() =dataSet.size

}//Fin de la class AdaptadorTaller