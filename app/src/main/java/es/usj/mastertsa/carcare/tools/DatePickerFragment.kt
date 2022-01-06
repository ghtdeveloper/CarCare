package es.usj.mastertsa.carcare.tools

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import java.util.*

/**
 ****Creado por: Edison Martinez,
 ****Fecha:04,Tuesday,2022,
 ****Hora: 10:36 PM.
 **/
class DatePickerFragment : DialogFragment()
{
    private var listener : DatePickerDialog.OnDateSetListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog
    {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(requireActivity(),listener,year,month,day)
    }//Fin del metodo onCreateDialog

    companion object {
        fun newInstance(listener: DatePickerDialog.OnDateSetListener): DatePickerFragment {
            val fragment = DatePickerFragment()
            fragment.listener = listener
            return fragment
        }
    }
}//Fin de la class DatePickerFragment