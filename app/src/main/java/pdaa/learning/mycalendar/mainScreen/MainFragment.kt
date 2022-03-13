package pdaa.learning.mycalendar.mainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pdaa.learning.mycalendar.CalendarViewModel
import pdaa.learning.mycalendar.R
import pdaa.learning.mycalendar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    /*private val viewModel: CalendarViewModel by lazy {
        ViewModelProvider(this).get(CalendarViewModel::class.java)
    }*/

    private lateinit var viewModel:CalendarViewModel
    private lateinit var binding:FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_main,container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CalendarViewModel::class.java)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recordGrid.adapter = RecordGridAdapter(RecordGridAdapter.OnClickListener{ record ->
            //Toast.makeText(context, "Se ha pulsado el botón ${record.title}", Toast.LENGTH_LONG).show()
            viewModel.showDetailsOfTheRecord(record)
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToDetailFragment())
        })

        binding.addNewRecord.setOnClickListener {
            this.findNavController().navigate(MainFragmentDirections.actionMainFragmentToNewDateFragment())
        }

        return binding.root
    }

    companion object {

    }
}