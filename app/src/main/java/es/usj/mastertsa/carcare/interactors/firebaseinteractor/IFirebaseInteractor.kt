package es.usj.mastertsa.carcare.interactors.firebaseinteractor

import com.google.firebase.firestore.CollectionReference


/**
 ****Creado por: Edison Martinez,
 ****Fecha:07,Monday,2022,
 ****Hora: 7:39 PM.
 **/
interface IFirebaseInteractor
{
    fun getAllMark() : CollectionReference

}