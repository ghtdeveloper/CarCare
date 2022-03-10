package es.usj.mastertsa.carcare.presenter

import android.util.Log
import com.google.firebase.firestore.CollectionReference
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.interactors.firebaseinteractor.FirebaseInteractor


/**
 ****Creado por: Edison Martinez,
 ****Fecha:07,Monday,2022,
 ****Hora: 7:29 PM.
 **/
class FirebasePresenter : ContractInterface.IFirebasePresenter
{
    //Objects Interactor
    private var interactor : FirebaseInteractor? = null

    init
    {
       interactor = FirebaseInteractor(this)

    }

    override fun getListMarks(): CollectionReference
    {
        return  interactor!!.getAllMark()
    }

}