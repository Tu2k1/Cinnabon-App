package com.example.cinnabonapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cinnabonapp.R
import com.example.cinnabonapp.databinding.FragmentPickupDateBinding
import com.example.cinnabonapp.viewmodel.CinnabonViewModel


class PickupDateFragment : Fragment() {
    private var _binding: FragmentPickupDateBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: CinnabonViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_pickup_date,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner
            pickupFragment = this@PickupDateFragment
        }
    }
    fun nextScreen(){
        findNavController().navigate(R.id.action_pickupDateFragment_to_summaryFragment)
    }
    fun cancelOrder(){
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupDateFragment_to_startFragment)
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}