package es.usj.mastertsa.carcare.ui.vehiculos

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import database.entities.Vehiculo
import es.usj.mastertsa.carcare.R
import es.usj.mastertsa.carcare.ui.principal.MainActivity
import org.w3c.dom.Text

class VehiculosAdapter(val vehiculos:List<Vehiculo>):RecyclerView.Adapter<VehiculosAdapter.VehiculoHolder>() {

//    lateinit var vehiculoInfo : TextView
//    lateinit var vehiculoChasis : TextView
//    lateinit var VehiculoTipo : TextView

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
        holder.render(vehiculos[position])
    }

    override fun getItemCount(): Int = vehiculos.size

    class VehiculoHolder(val view: View):RecyclerView.ViewHolder(view){

        fun render(vehiculo: Vehiculo){

            view.findViewById<TextView>(R.id.txtViewChasis).text = vehiculo.chasis
            view.findViewById<TextView>(R.id.txtViewTipoVehiculo).text = vehiculo.colorVehiculor
            view.findViewById<TextView>(R.id.txtViewInfoVehiculo).text = if (!vehiculo.alias.isNullOrBlank())  vehiculo.alias else  vehiculo.marca + " " +vehiculo.modelo

            view.setOnClickListener {
                val dialogBuilder = AlertDialog.Builder(view.context)

                val mView: View =
                    LayoutInflater.from(view.getRootView().getContext()).inflate(R.layout.fragment_registrar_vehiculo, null)

//                val dialogView =  mView.inflate(R.layout.fragment_registrar_vehiculo,null)
                dialogBuilder.setView(mView)

                val alertDialog = dialogBuilder.create()
                alertDialog.setTitle("Editar Vehiculo")
                alertDialog.show() }

        }
    }
}