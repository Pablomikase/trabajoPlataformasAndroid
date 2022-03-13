package pdaa.learning.mycalendar.confirmation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pdaa.learning.mycalendar.CalendarViewModel
import pdaa.learning.mycalendar.R
import pdaa.learning.mycalendar.databinding.FragmentConfirmationBinding


class ConfirmationFragment : Fragment() {

    /*private val viewModel: CalendarViewModel by lazy {
        ViewModelProvider(this).get(CalendarViewModel::class.java)
    }*/
    private lateinit var viewModel:CalendarViewModel

    private lateinit var binding:FragmentConfirmationBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(inflater, R.layout.fragment_confirmation, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CalendarViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        configureListeners()
        //configureContent()
        return binding.root
    }

    private fun configureContent() {
        binding.title.text = viewModel.newRecord.value!!.title
        binding.eventKind.text = viewModel.newRecord.value!!.kind.toString()
        binding.startDate.text = viewModel.newRecord.value!!.startDate
        binding.endDate.text = viewModel.newRecord.value!!.endDate
        binding.description.text = viewModel.newRecord.value!!.description
        binding.allDay.text = viewModel.allDay.toString()
    }

    private fun configureListeners() {
        binding.cancelButtonConfirmation.setOnClickListener {
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToNewDateFragment())
        }

        binding.confirmButton.setOnClickListener {
            viewModel.saveNewRecord()
            findNavController().navigate(ConfirmationFragmentDirections.actionConfirmationFragmentToMainFragment2())
        }
    }


}