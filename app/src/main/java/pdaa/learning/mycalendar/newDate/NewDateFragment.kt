package pdaa.learning.mycalendar.newDate

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pdaa.learning.mycalendar.CalendarViewModel
import pdaa.learning.mycalendar.Kind
import pdaa.learning.mycalendar.R
import pdaa.learning.mycalendar.databinding.FragmentNewDateBinding
import java.util.*


class NewDateFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentNewDateBinding
    val NEW_SPINNER_ID = 1
    private val TAG = "NewDateFragment"
    private val kindOfElementList = arrayOf(
        Kind.CITA, Kind.ANIVERSARIO, Kind.CUENTA_ATRAS
    )
    /*private val viewModel: CalendarViewModel by lazy {
        ViewModelProvider(this).get(CalendarViewModel::class.java)
    }*/
    private lateinit var viewModel:CalendarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewDateBinding.inflate(inflater)
        viewModel = ViewModelProvider(requireActivity()).get(CalendarViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        configureClickListeners()
        configureSpinner()
        return binding.root
    }


    private fun configureClickListeners() {
        binding.btnDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    //Que hacer cuando se selecciona la fecha
                    viewModel.setNewDateRecordValues(year, month, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        binding.btnTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    viewModel.setNewHourRecordValues(hourOfDay, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }

        binding.btnEndDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePickerDialog = DatePickerDialog(
                context!!,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    //Que hacer cuando se selecciona la fecha
                    viewModel.setNewEndDateRecordValues(year, month, dayOfMonth)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
        }

        binding.btnEndTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePickerDialog = TimePickerDialog(
                context,
                TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                    viewModel.setEndNewHourRecordValues(hourOfDay, minute)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            )
            timePickerDialog.show()
        }

        binding.cancelButton.setOnClickListener {
            findNavController().navigate(NewDateFragmentDirections.actionNewDateFragmentToMainFragment())
        }

        binding.continueButton.setOnClickListener {

            if(binding.eventTitle.text.isNullOrEmpty() || binding.eventDescription.text.isNullOrEmpty()){
                Toast.makeText(context, "Los datos están incompletos.", Toast.LENGTH_LONG).show()
            }else{
                viewModel.generateRegisterToSave(
                    binding.eventTitle.text.toString(),
                    binding.eventDescription.text.toString(),
                    binding.allDay.isChecked
                )
                findNavController().navigate(NewDateFragmentDirections.actionNewDateFragmentToConfirmationFragment())
            }


        }

    }

    private fun configureSpinner() {

        val arrayAdapter = ArrayAdapter(
            context!!,
            R.layout.support_simple_spinner_dropdown_item,
            kindOfElementList
        )
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item)
        with(binding.kindOfEvent) {
            adapter = arrayAdapter
            setSelection(0, false)
            onItemSelectedListener = this@NewDateFragment
            prompt = "Selecciona el tipo de evento que quieres crear"
            gravity = Gravity.CENTER
        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.setKindOfRecord(kindOfElementList[position])

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        Toast.makeText(context, "Selecciona un tipo de evento.", Toast.LENGTH_LONG).show()
        viewModel.setKindOfRecord(kindOfElementList[0])
    }

}