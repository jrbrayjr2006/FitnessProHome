package com.continuouswave1550.fitness.fitnessprohome.myfitness

import android.os.Bundle
import android.os.Handler
import android.view.*
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.continuouswave1550.fitness.fitnessprohome.R
import com.continuouswave1550.fitness.fitnessprohome.databinding.FragmentMyFitnessBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * An example full-screen fragment that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
@AndroidEntryPoint
class MyFitnessFragment : Fragment() {
    private lateinit var binding: FragmentMyFitnessBinding
    @Inject lateinit var viewModel: MyFitnessViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_fitness, container,false)
        binding.scanButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_myFitnessFragment_to_QRScanFragment)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, requireView().findNavController()) || super.onOptionsItemSelected(item)
    }
}