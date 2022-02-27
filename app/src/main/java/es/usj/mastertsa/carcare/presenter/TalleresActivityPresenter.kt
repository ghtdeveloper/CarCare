package es.usj.mastertsa.carcare.presenter

import androidx.lifecycle.ViewModel
import es.usj.mastertsa.carcare.contract.ContractInterface
import es.usj.mastertsa.carcare.domain.Taller
import es.usj.mastertsa.carcare.interactors.tallerinteractor.ITallerInteractor


/**
 ****Creado por: Edison Martinez,
 ****Fecha:17,Thursday,2022,
 ****Hora: 8:59 PM.
 **/
class TalleresActivityPresenter (private val iTallerInteractor: ITallerInteractor ) :
    ContractInterface.ITallerPresenter,ViewModel()
{
    override fun findAll(): List<Taller> {
      return iTallerInteractor.findAll()
    }

    override fun save(taller: Taller) {
        iTallerInteractor.save(taller)
    }

}