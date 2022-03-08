package es.usj.mastertsa.carcare.interactors.firebaseinteractor

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import es.usj.mastertsa.carcare.presenter.FirebasePresenter


/**
 ****Creado por: Edison Martinez,
 ****Fecha:07,Monday,2022,
 ****Hora: 7:29 PM.
 **/
class FirebaseInteractor(private val presenter : FirebasePresenter) : IFirebaseInteractor
{
    //TAG
    private var TAG : String = this.javaClass.name
    //Object Firebase
    private var db = Firebase.firestore

    override fun getAllMark(): CollectionReference
    {
         Log.d(TAG,"Get All Marks Vehicle")
          return  db.collection("Collect_Vehiculos_Etrp").document("marcas")
          .collection("Collect_Marcas")
    }

}