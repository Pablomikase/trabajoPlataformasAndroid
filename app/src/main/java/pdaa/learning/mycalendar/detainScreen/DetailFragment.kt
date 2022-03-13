package pdaa.learning.mycalendar.detainScreen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import pdaa.learning.mycalendar.CalendarViewModel
import pdaa.learning.mycalendar.R
import pdaa.learning.mycalendar.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {

    private lateinit var viewModel:CalendarViewModel
    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewModel = ViewModelProvider(requireActivity()).get(CalendarViewModel::class.java)

        binding. lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

}