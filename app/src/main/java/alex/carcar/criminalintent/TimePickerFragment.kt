package alex.carcar.criminalintent

import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*

private const val ARG_TIME = "time"

class TimePickerFragment : DialogFragment() {

    interface Callbacks {
        fun onTimeSelected(date: Date)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val timeListner =
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.HOUR, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val resultDate: Date = calendar.time;
                targetFragment?.let { fragment ->
                    (fragment as Callbacks).onTimeSelected(resultDate)
                }
            }
        val time = arguments?.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = time
        val initialHour = calendar.get(Calendar.HOUR)
        val initialMinute = calendar.get(Calendar.MINUTE)
        return TimePickerDialog(requireContext(), timeListner, initialHour, initialMinute, false)
    }

    companion object {
        fun newInstance(date: Date): TimePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_TIME, date)
            }
            return TimePickerFragment().apply {
                arguments = args
            }
        }
    }
}